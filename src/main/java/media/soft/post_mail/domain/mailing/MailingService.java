package media.soft.post_mail.domain.mailing;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailingService {

    private final MailingRepository mailingRepository;

    public Mailing createAndValidatingMailing(Mailing mailing) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        Set<ConstraintViolation<Mailing>> violations = validator.validate(mailing);
        if (!violations.isEmpty()) {
            val violationMsgBuilder = new StringBuilder();
            for (ConstraintViolation<Mailing> violation : violations) {
                violationMsgBuilder.append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(violationMsgBuilder.toString());
        }
        mailing.setStatus(MailingStatus.ACCEPTED);
        mailing.setMovesCounter(0);
        return mailingRepository.save(mailing);
    }

    public Mailing setRouteMailingData(Mailing mailing) {
        mailing.setMovesCounter(mailing.getMovesCounter() + 1);
        mailing.setStatus(MailingStatus.ON_WAY);
        return mailingRepository.save(mailing);
    }

    public Mailing setEndArrivalMailingData(Mailing mailing) {
        mailing.setMovesCounter(mailing.getMovesCounter() + 1);
        mailing.setStatus(MailingStatus.AT_POINT_OF_ISSUE);
        return mailingRepository.save(mailing);
    }

    public Mailing setIssueMailingData(Mailing mailing) {
        mailing.setMovesCounter(mailing.getMovesCounter() + 1);
        mailing.setStatus(MailingStatus.ISSUED);
        return mailingRepository.save(mailing);
    }

    public MailingStatus getCurrentStatus(Long mailingId) {
        Objects.requireNonNull(mailingId, "Empty mailing id");
        return mailingRepository.findById(mailingId)
                .orElseThrow(() -> new IllegalArgumentException("Wrong mailing id")).getStatus();
    }

    @ResponseBody
    public List<StoryPoint> getStory(Long mailingId) {
        Objects.requireNonNull(mailingId, "Empty mailing id");
        return mailingRepository.findById(mailingId)
                .orElseThrow(() -> new IllegalArgumentException("Wrong mailing id"))
                .getMoves()
                .stream()
                .map(m -> new StoryPoint(m.getOffice(), m.getMoveType(), m.getPostalMoveNumber()))
                .sorted(Comparator.comparing(StoryPoint::getPointNumber))
                .collect(Collectors.toList());
    }
}
