package kevin.study.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kevin.study.blog.domain.Role;
import kevin.study.blog.domain.User;
import kevin.study.blog.dto.UserDto;
import kevin.study.blog.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    //회원 가입
    @Transactional
    public void insertUser(UserDto userDto) {
        String encPassword = encoder.encode(userDto.getPassword());
        User user = User.builder()
                        .username(userDto.getUsername())
                        .password(encPassword)
                        .email(userDto.getEmail())
                        .build();
        user.setRole(Role.USERR);
        userRepository.save(user);
    }

    //Username 중복 검사
    @Transactional(readOnly = true)
    public boolean validateDuplicateUser(String username) {
        return userRepository.existsByUsername(username);
    }

    //로그인
    @Transactional(readOnly = true)
    public User loginUser(User user) {
        return userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다: " + user.getUsername()));
    }

    //회원 정보 전체 목록
    @Transactional(readOnly = true)
    public List<User> userList() {
        return userRepository.findAll();
    }

    //회원 찾기
    @Transactional(readOnly = true)
    public User findUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다: " + username));
    }

    //회원 정보 수정
    @Transactional
    public void updateUser(User user) {
        User persistenceUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        String encPassword = encoder.encode(user.getPassword());
        persistenceUser.setPassword(encPassword);
        persistenceUser.setEmail(user.getEmail());
    }

    //회원 권한 수정
    @Transactional
    public void updateUserRole(User user) {
        User persistenceUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        persistenceUser.setRole(user.getRole());
    }

}
