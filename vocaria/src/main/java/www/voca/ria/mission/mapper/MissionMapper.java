package www.voca.ria.mission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.mission.model.MissionVO;
import www.voca.ria.party.model.AccountVO;

@Mapper
public interface MissionMapper extends GeneralMapper {
	
	public List<MissionVO> listAllMission(@Param("accountId") String accountId,
			@Param("date") String dateString, @Param("page") PageDTO page);
	public long countAllMission(@Param("accountId") String accountId,
			@Param("date") String dateString);

	
	public int evaluate(@Param("questionIdList") List<String> questionIdList,
			@Param("chooseList") List<String> chooseList);

	
	public int setupTodayMission(@Param("student") AccountVO student);
}
