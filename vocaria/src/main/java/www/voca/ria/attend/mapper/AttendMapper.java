package www.voca.ria.attend.mapper;

import org.apache.ibatis.annotations.Mapper;

import www.voca.ria.framework.mapper.GeneralMapper;

@Mapper
public interface AttendMapper extends GeneralMapper {
	
	public int attendAll(String userId);

}
