package www.voca.ria.mission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.party.model.AccountVO;

@Mapper
public interface MissionMapper extends GeneralMapper {	
	public int setupTodayMission(@Param("student") AccountVO student);

	public int evaluate(@Param("questionIdList") List<String> questionIdList,
			@Param("chooseList") List<String> chooseList);

}
