package org.example.stayeasy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Hanterar CORS
//Tillåter frontend på andra localhost-portar att kommunicera med backend-API:et.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //Anger vilka webbadresser som får göra CORS-anrop
        registry.addMapping("/**")
                .allowedOriginPatterns("http://127.0.0.1:8081*",  "http://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false);
    }

    //Tillåter inte andra credentials tex cookies i CORS anrop
}
