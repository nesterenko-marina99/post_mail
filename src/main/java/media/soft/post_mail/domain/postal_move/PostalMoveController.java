package media.soft.post_mail.domain.postal_move;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postal_move")
public class PostalMoveController {

    private final PostalMoveService postalMoveService;

    @PostMapping(value = "/create")
    public ResponseEntity<PostalMove> registerNewMailingAtOffice(@Valid @RequestBody PostalMove postalMove) {
        return new ResponseEntity<>(postalMoveService.registerFirstMove(postalMove), HttpStatus.CREATED);
    }

    @PostMapping(value = "/departure")
    public ResponseEntity<PostalMove> registerDeparture(@Valid @RequestBody PostalMove postalMove) {
        return new ResponseEntity<>(postalMoveService.registerDeparture(postalMove), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/arrival")
    public ResponseEntity<PostalMove> registerArrival(@Valid @RequestBody PostalMove postalMove) {
        return new ResponseEntity<>(postalMoveService.registerArrival(postalMove), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/issue")
    public ResponseEntity<PostalMove> issueMailing(@Valid @RequestBody PostalMove postalMove) {
        return new ResponseEntity<>(postalMoveService.issuanceOfMailingToAddressee(postalMove), HttpStatus.OK);
    }
}
