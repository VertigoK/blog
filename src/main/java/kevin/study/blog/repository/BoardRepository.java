package kevin.study.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kevin.study.blog.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
