package pro.developia.shop.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.developia.shop.core.ApiResult;
import pro.developia.shop.core.Code;
import pro.developia.shop.domain.user.MyUserDetails;
import pro.developia.shop.domain.user.User;
import pro.developia.shop.dto.user.UserSignInDTO;
import pro.developia.shop.dto.user.UserSignUpDTO;
import pro.developia.shop.service.user.UserService;
import pro.developia.shop.utils.TokenUtils;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users/auths")
public class UserAuthController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;


    @ApiOperation(value = "회원가입", notes = "유저 정보를 통해 회원가입을 진행한다.")
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Validated @RequestBody UserSignUpDTO userSignUpDTO, Errors errors) {
        //todo - aop 사용
        //validation check
        if (errors.hasErrors()) {
            log.error("회원가입 validation error -> {}", userSignUpDTO);
            return ResponseEntity.badRequest().body(ApiResult.fail(Code.BAD_REQUEST, errors));
        }

        //이메일 중복체크
        boolean emailDuplicated = userService.isEmailDuplicated(userSignUpDTO.getEmail());
        if (emailDuplicated) {
            return ResponseEntity.badRequest().body(ApiResult.fail(Code.BAD_REQUEST, "이미 가입된 이메일입니다."));
        }

        //회원가입
        User user = userService.signUp(userSignUpDTO);
        return ResponseEntity.ok(ApiResult.ok(user.getId(), "회원가입에 성공했습니다."));
    }


    @ApiOperation(value = "로그인", notes = "이메일, 비밀번호룰 통해 로그인울 진행한다.")
    @PostMapping("/sign-in")
    public ResponseEntity signIn(@Validated @RequestBody UserSignInDTO userSignInDTO, Errors errors) {
        //todo - aop 사용
        //validation check
        if (errors.hasErrors()) {
            log.error("로그인 validation error -> {}", userSignInDTO);
            return ResponseEntity.badRequest().body(ApiResult.fail(Code.BAD_REQUEST, errors));
        }

        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(userSignInDTO.getEmail());

        String token = TokenUtils.generateJwtToken(makeUser(userDetails));
        return ResponseEntity.ok(ApiResult.ok(token));
    }



    public User makeUser(MyUserDetails userDetails) {
        return User.builder()
                .id(userDetails.getId())
                .name(userDetails.getName())
                .nickName(userDetails.getNickName())
                .phone(userDetails.getPhone())
                .email(userDetails.getEmail())
                .gender(userDetails.getGender())
                .role(userDetails.getRole())
                .build();
    }


}

