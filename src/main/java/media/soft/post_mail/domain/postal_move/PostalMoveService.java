package media.soft.post_mail.domain.postal_move;

import lombok.RequiredArgsConstructor;
import lombok.val;
import media.soft.post_mail.domain.mailing.MailingRepository;
import media.soft.post_mail.domain.mailing.MailingService;
import media.soft.post_mail.domain.post_office.PostOfficeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostalMoveService {

    private final PostalMoveRepository moveRepository;
    private final MailingRepository mailingRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final MailingService mailingService;

    public PostalMove registerFirstMove(PostalMove postalMove) {
        val mailing = mailingService.createAndValidatingMailing(postalMove.getMailing());
        Objects.requireNonNull(postalMove.getOffice().getId(), "Empty office id");
        val office = postOfficeRepository.findById(postalMove.getOffice().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong office id"));
        if (mailing.getRecipientIndex().equalsIgnoreCase(office.getIndex())) {
            throw new IllegalArgumentException("You cannot send mailing from office of receipt");
        }
        postalMove.setPostalMoveNumber(mailing.getMovesCounter());
        postalMove.setMoveType(MoveType.ARRIVAL);
        return moveRepository.save(postalMove);
    }

    public PostalMove registerDeparture(PostalMove postalMove) {
        Objects.requireNonNull(postalMove.getMailing().getId(), "Empty mailing id");
        var mailing = mailingRepository.findById(postalMove.getMailing().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong mailing id"));
        Objects.requireNonNull(postalMove.getOffice().getId(), "Empty office id");
        val office = postOfficeRepository.findById(postalMove.getOffice().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong office id"));
        if (mailing.getRecipientIndex().equalsIgnoreCase(office.getIndex())) {
            throw new IllegalArgumentException("You cannot send mailing from office of receipt");
        }
        mailing = mailingService.setRouteMailingData(mailing);
        postalMove.setPostalMoveNumber(mailing.getMovesCounter());
        postalMove.setMoveType(MoveType.DEPARTURE);
        return moveRepository.save(postalMove);
    }

    public PostalMove registerArrival(PostalMove postalMove) {
        Objects.requireNonNull(postalMove.getMailing().getId(), "Empty mailing id");
        var mailing = mailingRepository.findById(postalMove.getMailing().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong mailing id"));
        Objects.requireNonNull(postalMove.getOffice().getId(), "Empty office id");
        val office = postOfficeRepository.findById(postalMove.getOffice().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong office id"));
        if (mailing.getRecipientIndex().equalsIgnoreCase(office.getIndex())) {
            mailing = mailingService.setEndArrivalMailingData(mailing);
        } else {
            mailing = mailingService.setRouteMailingData(mailing);
        }
        postalMove.setPostalMoveNumber(mailing.getMovesCounter());
        postalMove.setMoveType(MoveType.ARRIVAL);
        return moveRepository.save(postalMove);
    }

    public PostalMove issuanceOfMailingToAddressee(PostalMove postalMove) {
        Objects.requireNonNull(postalMove.getMailing().getId(), "Empty mailing id");
        var mailing = mailingRepository.findById(postalMove.getMailing().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong mailing id"));
        Objects.requireNonNull(postalMove.getOffice().getId(), "Empty office id");
        val office = postOfficeRepository.findById(postalMove.getOffice().getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong office id"));
        if (!mailing.getRecipientIndex().equalsIgnoreCase(office.getIndex())) {
            throw new IllegalArgumentException("You cannot issue mailing from intermediate office");
        }
        mailing = mailingService.setIssueMailingData(mailing);
        postalMove.setPostalMoveNumber(mailing.getMovesCounter());
        postalMove.setMoveType(MoveType.DEPARTURE);
        return moveRepository.save(postalMove);
    }
}
