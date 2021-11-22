package pro.developia.shop.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Setter
@Getter
public class UserDto {

    @NotEmpty
    private Long id;

    @NotEmpty
    @Length(max=20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]{1,20}$", message = "한글, 영문 대소문자만 가능합니다.")
    private String name;

    @NotEmpty
    @Length(max = 100)
    @Email
    private String email;

    @NotEmpty
    @Length(max = 20)
    @Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", message = "휴대폰 번호가 맞지 않습니다.")
    private String phone;

    @Length(max = 1)
    @Pattern(regexp = "^[1-2]+$")
    private String gender;

}
