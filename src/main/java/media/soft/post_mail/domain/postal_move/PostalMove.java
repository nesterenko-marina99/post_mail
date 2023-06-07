package media.soft.post_mail.domain.postal_move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import media.soft.post_mail.domain.mailing.Mailing;
import media.soft.post_mail.domain.post_office.PostOffice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "postal_move")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostalMove {

    @Id
    @SequenceGenerator(name = "postal_move_id_seq", sequenceName = "\"public\".\"postal_move_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "postal_move_seq")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "mailing_id")
    private Mailing mailing;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "office_id")
    private PostOffice office;

    @Enumerated(EnumType.STRING)
    private MoveType moveType;

    private Integer postalMoveNumber;
}
