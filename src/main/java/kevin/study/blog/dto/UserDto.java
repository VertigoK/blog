package uniflow.blog.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message="Username must be input.")
    @Size(min=3, max=20, message="Username must be between 3 and 20 characters, inclusively.")
    private String username;

    @NotBlank(message="Password must be input.")
    @Size(min=5, max=12, message="Password must be between 5 and 12 characters, inclusively.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message="Email must be input.")
    @Email(message = "Email should be valid.")
    private String email;

}
