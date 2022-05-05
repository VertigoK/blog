package kevin.study.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import kevin.study.blog.domain.User;
import kevin.study.blog.dto.ResponseDto;
import kevin.study.blog.dto.UserDto;
import kevin.study.blog.service.UserService;

import javax.validation.Valid;

@RestController
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입
    @PostMapping("auth/signup")
    public ResponseDto<Integer> signup(@Valid @RequestBody UserDto userDto) {
        if (!userService.validateDuplicateUser(userDto.getUsername())) {
            userService.insertUser(userDto);
        } else {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), 1); //JSON {"400", 1}
        }
        return new ResponseDto<>(HttpStatus.OK.value(), 1); //JSON {"200": 1}
    }

    //로그인: Spring Security 에서 처리 -> SecurityConfig 에서 loginProcessingUrl("/auth/login") 설정

    //회원 정보 수정
    @PutMapping("/user/update")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    //회원 권한 수정
    @PutMapping("/admin/updateRole")
    public ResponseDto<Integer> updateRole(@RequestBody User user) {
        userService.updateUserRole(user);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    //Username 실시간 중복 검사
    @PostMapping("/auth/checkUsernameRealTime")
    public int checkUsernameRealTime(@RequestParam("username") String username) {
        return userService.validateDuplicateUser(username) ? 1 : 0;  //boolean to int
    }

}
