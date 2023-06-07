package media.soft.post_mail.domain.mailing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import media.soft.post_mail.domain.postal_move.PostalMove;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "mailing")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mailing {

    @Id
    @SequenceGenerator(name = "mailing_id_seq", sequenceName = "\"public\".\"mailing_id_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "mailing_id_seq")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MailingType mailingType;

    @NotBlank
    private String recipientIndex;

    @NotBlank
    private String recipientAddress;

    @NotBlank
    private String recipientName;

    @OneToMany(mappedBy = "mailing")
    private List<PostalMove> moves;

    private Integer movesCounter;

    @Enumerated(EnumType.STRING)
    private MailingStatus status;

}
