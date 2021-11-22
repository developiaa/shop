package pro.developia.shop.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserSignUpDTO {

    @NotEmpty
    @Length(max=20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]{1,20}$", message = "한글, 영문 대소문자만 가능합니다.")
    private String name;

    @NotEmpty
    @Length(max=30)
    @Pattern(regexp = "^[a-z]{1,30}$", message = "영문 소문자만 가능합니다.")
    private String nickName;

    @NotEmpty
    @Length(min = 10)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,}$", message = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여야 합니다.")
    private String password;

    @NotEmpty
    @Length(max = 20)
    @Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", message = "휴대폰 번호가 맞지 않습니다.")
    private String phone;

    @NotEmpty
    @Length(max = 100)
    @Email
    private String email;

    @Length(max = 1)
    @Pattern(regexp = "^[1-2]+$")
    private String gender;


    @Override
    public String toString() {
        return "UserSignUpDTO{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
