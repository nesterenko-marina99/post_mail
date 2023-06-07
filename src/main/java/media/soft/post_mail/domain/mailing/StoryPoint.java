package media.soft.post_mail.domain.mailing;

import lombok.Value;
import media.soft.post_mail.domain.post_office.PostOffice;
import media.soft.post_mail.domain.postal_move.MoveType;

@Value
public class StoryPoint {

    PostOffice office;
    MoveType moveType;
    Integer pointNumber;
}
