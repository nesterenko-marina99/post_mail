package media.soft.post_mail.domain.postal_move;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalMoveRepository extends JpaRepository<PostalMove, Long> {
}
