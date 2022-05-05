package kevin.study.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kevin.study.blog.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
