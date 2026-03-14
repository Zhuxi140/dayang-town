package com.dayang.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhuxi
 * @apiNote  全局用户认证上下文 (基于ThreadLocal)
 * <p>
 *     存入用户认证信息，用于全局调用
 * </p>
 */


public class AuthContext {

    private static final ThreadLocal<AuthUser> USER_CONTEXT = new ThreadLocal<>();

    /**
     * 保存用户认证信息
     * @param authUser 用户认证信息
     */
    public static void saveContext(AuthUser authUser){
        USER_CONTEXT.set(authUser);
    }

    /**
     * 获取完整用户认证信息
     * @return 用户认证信息
     */
    public static AuthUser getContext(){
        return USER_CONTEXT.get();
    }

    /**
     * 获取用户Id
     * @return 用户Id
     */
    public static Long getUserId(){
        AuthUser authUser = USER_CONTEXT.get();
        return authUser == null ? null : authUser.getUserId();
    }


    /**
     * 移除用户认证信息
     * 必须在finally中调用，否则线程变量可能被污染
     */
    public static void remove(){
        USER_CONTEXT.remove();
    }


    /**
     * 内部类，存储用户认证信息
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthUser{
        private Long userId;
/*        private String username;
        private Long shopId;*/
    }
}
