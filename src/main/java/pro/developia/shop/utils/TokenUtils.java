package pro.developia.shop.utils;

import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.developia.shop.domain.user.User;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenUtils {

    private static final String secretKey = "SecretKeyTEXT";

    /**
     * 유저 정보로 jwt 생성
     * @param user
     * @return
     */
    public static String generateJwtToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();

    }

    /**
     * 유효한 토큰인지 확인
     * @param token
     * @return
     */
    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);
            log.debug("expireTime : {}", claims.getExpiration());
            log.debug("email : {}", claims.get("email"));
            log.debug("id : {}", claims.get("id"));
//            log.debug("role : {}", claims.get("role"));
            return true;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Date createExpireDateForOneYear() {
        // 토큰 만료시간 설정
        Calendar c = Calendar.getInstance();
        // 1일
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;

    }

    private static Map<String, Object> createClaims(User user) {
        // 공개 클레임에 들어갈 정보 설정
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());
        return claims;
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    public static Object getUserIdFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.get("id");
    }

//    private static UserRole getRoleFromToken(String token) {
//        Claims claims = getClaimsFormToken(token);
//        return (UserRole) claims.get("role");
//    }


}

