package kevin.study.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kevin.study.blog.config.auth.PrincipalDetail;
import kevin.study.blog.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입 화면
    @GetMapping("/auth/signupForm")
    public String signupForm() {
        return "user/signupForm";
    }

    //로그인 화면
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    //회원 정보 수정 화면
    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
        //principal은 username과 password 정보만 가지고 있음
        model.addAttribute("sessionUser", userService.findUser(principal.getUsername()));
        return "user/updateForm";
    }

    //회원 정보 전체 목록 화면
    @GetMapping("/admin/userList")
    public String findUsers(Model model) {
        model.addAttribute("userList", userService.userList());
        return "user/userList";
    }

    //회원 권한 수정 화면
    @GetMapping("/admin/updateUser")
    public String findOne(@RequestParam String username, Model model) {
        model.addAttribute(("updateUser"), userService.findUser(username));
        return "user/updateRoleForm";
    }

}
