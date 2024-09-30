package www.voca.ria.mission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.framework.exception.BusinessException;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.framework.model.structure.Pair;
import www.voca.ria.mission.mapper.MissionMapper;
import www.voca.ria.mission.model.ChoiceDTO;
import www.voca.ria.mission.model.MissionVO;
import www.voca.ria.mission.strategy.AutomaticallyIteratingStrategy.QuestionIteratingTag;
import www.voca.ria.mission.strategy.DirectlyChoosingStrategy.QuestionAddress;
import www.voca.ria.mission.strategy.QuestionBuildStrategy;
import www.voca.ria.mission.strategy.QuestionBuildStrategy.StrategyType;
import www.voca.ria.party.model.AccountVO;

@Service
public class MissionService {
	@Autowired
	private MissionMapper missionMapper;
	
	public Pair<List<MissionVO>, PageDTO> listAllMissionFrom(
			String teacherId, String studentId, String dateString, int pageNum) {
		PageDTO page = new PageDTO(pageNum);
		
		List<MissionVO> missionList = missionMapper
				.listAllMissionFrom(teacherId, studentId, dateString, page);
		
		page.buildPagination(missionMapper
				.countAllMissionFrom(teacherId, studentId, dateString));
		
		return new Pair<>(missionList, page);
	}
	
	public Pair<List<MissionVO>, PageDTO> listAllMissionTo(
			String studentId, String dateString, int pageNum) {
		PageDTO page = new PageDTO(pageNum);
			List<MissionVO> missionList = missionMapper
				.listAllMissionTo(studentId, dateString, page);
		
		page.buildPagination(missionMapper
				.countAllMissionTo(studentId, dateString));
		
		return new Pair<>(missionList, page);
	}
	
	public int evaluate(AccountVO student, ChoiceDTO choice) {
		List<String> questionIdList = choice.getQuestionIdList();
		List<String> chooseList = choice.getChooseList();
		
		MissionVO mission = MissionVO.builder()
				.maker(student)
				.isViewed(true)
				.isComplete(false)
				.build();
		
		if (mission.getId() == null) {
			missionMapper.insertMission(mission, student);
		}

		missionMapper.evaluate(mission.getId(), questionIdList, chooseList);
		
		int correctNum = missionMapper.countAnswerChoice(mission.getId());
		
		return correctNum;
	}
	
	public boolean buildMission(List<AccountVO> studentsList,
			QuestionBuildStrategy strategy, List<String> parsedData)
					throws BusinessException {
		return strategy.getType() == StrategyType.AUTO
				? buildMissionAutomatically(studentsList, parsedData)
				: buildMissionDirectly(studentsList, parsedData);
	}

	private boolean buildMissionAutomatically(List<AccountVO> studentsList,
			List<String> parsedData) {
		List<QuestionIteratingTag> tagList = parsedData.stream()
				.map(QuestionIteratingTag::fromString)
				.collect(Collectors.toList());
		
		return missionMapper.buildMissionAutomatically(studentsList, tagList);
	}

	private boolean buildMissionDirectly(List<AccountVO> studentsList,
			List<String> parsedData) {
		List<QuestionAddress> addressList = parsedData.stream()
				.map(QuestionAddress::fromString)
				.collect(Collectors.toList());
		
		return missionMapper.buildMissionDirectly(studentsList, addressList);
	}
}
