package pro.developia.shop.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pro.developia.shop.interceptor.JwtInterceptor;
import pro.developia.shop.interceptor.LogInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 추가
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                ;


        registry.addInterceptor(new JwtInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs", "/webjars/**")
                .excludePathPatterns("/v1/users/auths/**", "/error/**")
                ;

    }

}
