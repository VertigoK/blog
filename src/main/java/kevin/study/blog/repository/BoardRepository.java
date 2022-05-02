package uniflow.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uniflow.blog.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
