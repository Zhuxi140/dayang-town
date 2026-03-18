package com.dayang.util;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import com.dayang.constant.Enum.ErrorCodeEnum;
import com.dayang.exception.BizException;
import com.dayang.exception.SystemException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhuxi
 * @apiNote 加密工具类
 */

public class CryptoUtil {

    // TODO: 生产环境 应该 存储用户yml或Nacos中
    private static final String AES_SECRET = "DaYangTown_Super_Secret_Key_2026";

    private static final AES AES_INSTANCE = SecureUtil.aes(AES_SECRET.getBytes(StandardCharsets.UTF_8));


    /**
     * AES加密 (适用于手机号、身份证、真实姓名等)
     * @param data 待加密数据
     * @return 加密后的数据
     */
    public static String encryptAes(String data){
        if (StrUtil.isBlank( data)){
            return data;
        }
        try{
            return AES_INSTANCE.encryptHex(data);
        }catch (Exception e){
            throw new SystemException(ErrorCodeEnum.SYSTEM_ERROR, "AES加密失败",e);
        }
    }


    /**
     * AES解密 (适用于手机号、身份证、真实姓名等)
     * @param data 待解密数据
     * @return 解密后的数据
     */
    public static String decryptAes(String data){
        if (StrUtil.isBlank( data)){
            return data;
        }
        try{
            return AES_INSTANCE.decryptStr(data);
        }catch (Exception e){
            throw new SystemException(ErrorCodeEnum.SYSTEM_ERROR, "AES解密失败",e);
        }
    }

    /**
     * 生成SHA256加密哈希值
     * @param data 待加密数据
     * @return 哈希值
     */
    public static String hash(String data){
        if (StrUtil.isBlank( data)){
            return data;
        }
        return DigestUtil.sha256Hex(data);
    }

    /**
     * 密码加密
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password){
        if (StrUtil.isBlank(password)){
            throw new BizException(ErrorCodeEnum.PARAM_VALID_ERROR, "密码不能为空或含特殊符号");
        }
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    /**
     * 密码验证
     * @param password 密码
     * @param encryptedPassword 加密后的密码
     * @return 验证结果
     */
    public static boolean validatePassword(String password, String encryptedPassword){
        if (StrUtil.hasBlank(password,encryptedPassword)){
            return false;
        }
        return BCrypt.checkpw(password,encryptedPassword);
    }


    /**
     * 脱敏手机号
     * @param data 手机号
     * @return 脱敏后的手机号
     */
    public static String desensitizePhone(String data){
        if (StrUtil.isBlank(data) || !data.matches("^1[3-9]\\d{9}$")){
            return data;
        }
        return StrUtil.hide(data,3,7);
    }

    /**
     * 脱敏姓名
     * @param data 姓名
     * @return 脱敏后的姓名
     */
    public static String desensitizeName(String data){
        if (StrUtil.isBlank(data)){
            return data;
        }
        return DesensitizedUtil.chineseName( data);

    }

}
