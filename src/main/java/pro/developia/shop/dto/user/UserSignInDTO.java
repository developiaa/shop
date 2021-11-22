package pro.developia.shop.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserSignInDTO {

    @NotEmpty
    @Length(max = 100)
    @Email
    private String email;

    @NotEmpty
    @Length(min = 10)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,}$", message = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여야 합니다.")
    private String password;

}
