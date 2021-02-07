package mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonDictionaryMapper {

    public List<Object> getDictByTypeId(@Param("typeId") String typeId);




}