package com.ershiyi.mapper;

import com.ershiyi.dto.LocationRequestDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LocationMapper {
        @Insert("insert into  common_location ( studenterId, longiTude, latiTude, identification, position, city, province) values (#{studenterId},#{longiTude},#{latiTude},#{identification},#{position},#{city},#{province})")
        int getLocation(LocationRequestDTO localtionrequest);


        @Select("select * from  common_location where  studenterId =#{studenterId} order by  currentDt desc limit  1")
        LocationRequestDTO queryLocation(LocationRequestDTO localtionrequest);
}
