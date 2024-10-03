package www.voca.ria.mission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.mission.model.MissionVO;
import www.voca.ria.mission.strategy.AutomaticallyIteratingStrategy.QuestionIteratingTag;
import www.voca.ria.mission.strategy.DirectlyChoosingStrategy.QuestionAddress;
import www.voca.ria.party.model.AccountVO;

@Mapper
public interface MissionMapper extends GeneralMapper {
	public List<MissionVO> listAllMissionFrom(
			@Param("teacherId") String teacherId,
			@Param("studentId") String studentId,
			@Param("date") String dateString, @Param("page") PageDTO page);
	public long countAllMissionFrom(@Param("teacherId") String teacherId,
			@Param("studentId") String studentId, @Param("date") String dateString);
	
	public List<MissionVO> listAllMissionTo(@Param("accountId") String accountId,
			@Param("date") String dateString, @Param("page") PageDTO page);
	public long countAllMissionTo(@Param("accountId") String accountId,
			@Param("date") String dateString);
	
	public int countAnswerChoice(@Param("missionId") String missionId);
	
	
	public MissionVO getMissionById(String missionId);
	
	
	public int insertMission(@Param("mission") MissionVO missionVO,
			@Param("tester") AccountVO tester);
	
	public int evaluate(@Param("missionId") String missionId,
			@Param("questionIdList") List<String> questionIdList,
			@Param("chooseList") List<String> chooseList);

	
	public boolean buildMissionAutomatically(
			@Param("studantList") List<AccountVO> studentsList,
			@Param("tagList") List<QuestionIteratingTag> tagList);
	
	public boolean buildMissionDirectly(
			@Param("studantList") List<AccountVO> studentsList,
			@Param("addressList") List<QuestionAddress> addressList);
}
