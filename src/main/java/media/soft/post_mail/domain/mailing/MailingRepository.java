package media.soft.post_mail.domain.mailing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, Long> {
}
