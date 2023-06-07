package media.soft.post_mail.domain.post_office;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/office")
public class PostOfficeController {

    private final PostOfficeService postOfficeService;

    @PostMapping(value = "/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PostOffice getMailingStatus(@Valid @RequestBody PostOffice office) {
        return postOfficeService.createOffice(office);
    }
}
