package media.soft.post_mail.domain.post_office;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import media.soft.post_mail.domain.postal_move.PostalMove;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "post_office")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostOffice {

    @Id
    @SequenceGenerator(name = "post_office_id_seq", sequenceName = "\"public\".\"post_office_id_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "post_office_id_seq")
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String index;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "office")
    private List<PostalMove> moves;
}
