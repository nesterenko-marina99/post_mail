package media.soft.post_mail.domain.post_office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
