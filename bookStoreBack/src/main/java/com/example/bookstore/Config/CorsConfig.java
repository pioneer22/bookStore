package main.java.com.example.bookstore.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }
}

//@Configuration
//public class MyCorsConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                // 限制了路径和域名的访问
//        /*registry.addMapping("/api*").allowedOrigins("http://localhost:8081");*/
//                registry.addMapping("/**")
//                        .allowedOrigins(ALL)
//                        .allowedMethods(ALL)
//                        .allowedHeaders(ALL)
//                        .allowCredentials(true);
//            }
//        };
//    }
//}
