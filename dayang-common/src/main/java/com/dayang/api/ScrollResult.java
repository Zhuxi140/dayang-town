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
 * @apiNote 传统分页结果封装类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrollResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long total;
    private long current;
    private long size;
    private long pages;
    private List<T> records;

    /**
     * 创建分页结果
     * @param total 总数
     * @param current 当前页
     * @param size 每页大小
     * @param pages 总页数
     * @param records 数据
     * @param <T> 数据类型
     * @return 分页结果
     */
    public static <T> ScrollResult<T> of(long total, long current, long size,long pages, List<T> records){
        return new ScrollResult<>(total,current,size,pages,records);
    }

    /**
     * 空结果
     * @param current 当前页
     * @param size 每页大小
     * @param <T> 数据类型
     * @return 空结果
     */
    public static <T>  ScrollResult<T> empty(long current,long size){
        return new ScrollResult<>(0L,current,size,0L, Collections.emptyList());
    }
}
