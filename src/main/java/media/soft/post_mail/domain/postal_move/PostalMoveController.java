package media.soft.post_mail.domain.postal_move;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postal_move")
public class PostalMoveController {

    private final PostalMoveService postalMoveService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PostalMove registerNewMailingAtOffice(@Valid @RequestBody PostalMove postalMove) {
        return postalMoveService.registerFirstMove(postalMove);
    }

    @PostMapping(value = "/departure")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public PostalMove registerDeparture(@Valid @RequestBody PostalMove postalMove) {
        return postalMoveService.registerDeparture(postalMove);
    }

    @PostMapping(value = "/arrival")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public PostalMove registerArrival(@Valid @RequestBody PostalMove postalMove) {
        return postalMoveService.registerArrival(postalMove);
    }

    @PostMapping(value = "/issue")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PostalMove issueMailing(@Valid @RequestBody PostalMove postalMove) {
        return postalMoveService.issuanceOfMailingToAddressee(postalMove);
    }
}
