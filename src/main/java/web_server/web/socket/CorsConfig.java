package web_server.web.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config= new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*")); //모든 도메인 허용
        config.addAllowedMethod("*"); //모든 메소드 허용
        config.addAllowedHeader("*"); //모든 헤더 허용
        source.registerCorsConfiguration("/**",config); //모든 경로에 대해 설정 적용
        System.out.println("success");
        System.out.println("success2");

        return new CorsFilter(source);
    }
}

