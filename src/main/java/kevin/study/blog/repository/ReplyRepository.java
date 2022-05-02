package uniflow.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uniflow.blog.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
