package com.dayang.constant;


import com.dayang.constant.Enum.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;


/**
 * @author zhuxi
 * @apiNote 通用枚举转换器
 * <p>
 *     实现枚举类与数据库字段的相互映射
 * </p>
 *
 */
public class UniversalEnumHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public UniversalEnumHandler(Class<E> type) {
        if (type == null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getInt(columnIndex));
    }


    private E convert(int code) {
        for (E each : type.getEnumConstants()) {
            if (each.getCode() == code) {
                return each;
            }
        }

        throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by ordinal value.");
    }
}
