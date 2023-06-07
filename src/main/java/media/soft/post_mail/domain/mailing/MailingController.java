package media.soft.post_mail.domain.mailing;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mailing")
public class MailingController {

    private final MailingService service;

    @GetMapping(value = "/get_status")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public MailingStatus getMailingStatus(@RequestParam Long mailingId) {
        return service.getCurrentStatus(mailingId);
    }

    @GetMapping(value = "/get_story")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public List<StoryPoint> getMailingStory(@RequestParam Long mailingId) {
        val story = service.getStory(mailingId);
        return story;
    }
}
