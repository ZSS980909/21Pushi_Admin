package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface AbstractMapper<T> extends Mapper<T>, InsertListMapper<T> {
}
