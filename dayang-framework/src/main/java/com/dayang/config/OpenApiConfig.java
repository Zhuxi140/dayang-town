package com.dayang.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuxi
 * @apiNote OpenApi配置类
 */
@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("大洋镇核心业务接口文档")
                        .version("1.0.0")
                        .description("dayang-core-api")
                        .contact(new Contact()
                                .name("大洋")
                                .email("dadadada.com")
                        ));
    }
}
