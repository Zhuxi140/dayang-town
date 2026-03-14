package com.dayang.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhuxi
 * @apiNote Jackson配置类
 */
@Configuration
public class JacksonConfig {


    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder  builder){

        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 忽略未知属性（前端传了后端对象中不存的属性，直接忽略）
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //处理 Long精度丢失问题 统一返回给前端 String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);

        //处理 时间格式问题 统一格式为 yyyy-MM-dd HH:mm:ss
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(formatter));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(formatter));
        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }

}
