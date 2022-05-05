package kevin.study.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kevin.study.blog.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

}
