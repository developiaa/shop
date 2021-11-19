package pro.developia.shop.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import pro.developia.shop.constant.AuthConstants;
import pro.developia.shop.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
// 토큰의 유효성 검사를 진행
// 실패할 경우 예외 API Redirect
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws IOException {
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        log.debug("JwtInterceptor header -> {}", header);
        if (header != null) {
            String token = TokenUtils.getTokenFromHeader(header);
            if (TokenUtils.isValidToken(token)) {
                request.setAttribute("id", TokenUtils.getUserIdFromToken(token));
                return true;
            }
        }
        log.warn("JwtInterceptor unauthorized");
        response.sendRedirect("/error/unauthorized");
        return false;
    }
}
