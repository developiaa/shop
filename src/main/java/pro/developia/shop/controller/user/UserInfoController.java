package pro.developia.shop.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.developia.shop.core.ApiResult;
import pro.developia.shop.core.Code;
import pro.developia.shop.dto.user.UserDataDto;
import pro.developia.shop.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserInfoController {

    private final UserService userService;

    @ApiOperation(value = "단일 회원 상세 정보 조회", notes = "자신의 상세 정보를 조회한다.")
    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId, HttpServletRequest request) {
        log.info("단일 회원 상세 정보 조회 -> {}", userId);
        String id = String.valueOf(request.getAttribute("id"));

        //JWT 정보와 userId가 맞지 않은 경우
        if (!id.equals(String.valueOf(userId))) {
            log.info("JWT Mismatch");
            return ResponseEntity.badRequest().body(ApiResult.fail(Code.BAD_REQUEST, "Mismatch"));
        }

        return ResponseEntity.ok(ApiResult.ok(Code.SUCCESS, userService.getUserInfo(userId)));
    }

    @ApiOperation(value = "여러 회원 목록 조회",
            notes = "여러 회원의 목록을 조회한다. 이름, 이메일을 통해 검색이 가능하며 각 회원의 마지막 주문 정보를 가져온다.")
    @GetMapping
    public ResponseEntity getAllUser(@RequestParam(required = false, defaultValue = "") String email,
                                     @RequestParam(required = false, defaultValue = "") String name,
                                     @PageableDefault(size=10) Pageable pageable) {
        log.info("여러 회원 목록 조회");

        Page<UserDataDto> result = userService.findAll(email, name, pageable);
        return ResponseEntity.ok(ApiResult.ok(Code.SUCCESS,result));
    }

}
