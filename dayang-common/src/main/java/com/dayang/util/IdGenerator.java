package com.dayang.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author zhuxi
 * @apiNote Id生成器
 */
public class IdGenerator {

    /**
     * 生成主键Id
     * @return 主键
     */
    public static Long generateMainKey(){
        return IdUtil.getSnowflakeNextId();
    }

    /**
     * 生成TraceId
     * @return TraceId
     */
    public static String generateTraceId(){
        return IdUtil.fastSimpleUUID().toLowerCase();
    }


}
