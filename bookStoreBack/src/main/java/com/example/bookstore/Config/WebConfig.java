package main.java.com.example.bookstore.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //映射图片保存地址
        registry.addResourceHandler("/Img/**").addResourceLocations("file:E:/inteillJProject/bookstore/src/main/resources/static/Img/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
