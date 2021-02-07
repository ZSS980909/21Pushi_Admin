package mapper;

import com.ershiyi.domain.CommonPrison;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface CommonMapper extends AbstractMapper<CommonPrison> {
    public List<CommonPrison> getPrison(CommonPrison commonPrison);

    public List<CommonPrison> getAllPrison();
}