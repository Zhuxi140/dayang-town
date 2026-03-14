package com.dayang.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 游标分页结果封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursorResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nextCursor;
    private Boolean hasNext;
    private List<T> records;

    /**
     * 创建分页结果
     * @param nextCursor 下一页游标
     * @param hasNext 是否有下一页
     * @param records  数据
     * @param <T> 数据类型
     * @return 分页结果
     */
    public static <T> CursorResult<T> of(String nextCursor, Boolean hasNext, List<T> records){
        return new CursorResult<>(nextCursor,hasNext,records);
    }

    /**
     * 空结果
     * @return 空结果
     */
    public static <T> CursorResult<T> empty(){
        return new CursorResult<>(null,false, Collections.emptyList());
    }
}
