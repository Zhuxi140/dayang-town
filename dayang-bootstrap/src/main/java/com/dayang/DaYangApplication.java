package com.dayang;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhuxi
 * @apiNote 启动类
 */

@SpringBootApplication(scanBasePackages = "com.dayang")
@MapperScan(basePackages = "com.dayang", annotationClass = Mapper.class)
public class DaYangApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaYangApplication.class, args);
    }
}
