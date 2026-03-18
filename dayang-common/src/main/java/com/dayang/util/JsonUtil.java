package com.dayang.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dayang.constant.Enum.ErrorCodeEnum;
import com.dayang.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote Json工具类
 * <p>
 *     封装json转换，提供多种操作Json方法
 * </p>
 */

@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static{

        // 忽略null属性
        MAPPER.setDefaultPropertyInclusion(JsonInclude.Value.construct(
                JsonInclude.Include.NON_NULL,
                JsonInclude.Include.NON_NULL
        ));

        // 忽略未知属性
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * 对象转json
     * @param obj Java对象
     * @return  json
     */
    public static String toJsonString(Object obj){
        if (obj == null){
            return null;
        }
        if (obj instanceof String){
            return (String) obj;
        }

        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SystemException(ErrorCodeEnum.OBJECT_TO_JSON_ERROR,e);
        }
    }


    /**
     * json转对象
     * @param json json
     * @param clazz 类型
     * @return  对象
     */
    public static <T> T toObject(String json,Class<T> clazz){
        if (StrUtil.isBlank(json)){
            return null;
        }
        try {
            return MAPPER.readValue(json,clazz);
        } catch (JsonProcessingException e) {
            throw new SystemException(ErrorCodeEnum.JSON_TO_OBJECT_ERROR,e);
        }
    }

    /**
     * json转List
     * @param json json
     * @param clazz 类型
     * @return  List
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        if (StrUtil.isBlank(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new SystemException(ErrorCodeEnum.JSON_TO_OBJECT_ERROR, e);
        }
    }
}
