package www.voca.ria.mission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.mission.mapper.MissionMapper;
import www.voca.ria.mission.model.ChoiceDTO;
import www.voca.ria.mission.model.MissionDTO;
import www.voca.ria.mission.strategy.QuestionBuildStrategy;
import www.voca.ria.mission.strategy.QuestionBuildStrategy.StrategyType;
import www.voca.ria.party.model.AccountVO;

@Service
public class MissionService {
	@Autowired
	private MissionMapper missionMapper;
	
	public MissionDTO getMission(String accountId, String dateString) {
		
		return null;
	}
	
	public int setupTodayMission(AccountVO student) {
		return missionMapper.setupTodayMission(student);
	}
	
	public boolean buildMission(QuestionBuildStrategy strategy, List<String> parsedData) {
		return strategy.getType() == StrategyType.AUTO
				? buildMissionAutomatically(parsedData)
				: buildMissionDirectly(parsedData);
	}

	private boolean buildMissionAutomatically(List<String> parsedData) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean buildMissionDirectly(List<String> parsedData) {
		// TODO Auto-generated method stub
		return false;
	}

	public int evaluate(AccountVO student, ChoiceDTO choice) {
		List<String> questionIdList = choice.getQuestionIdList();
		List<String> chooseList = choice.getChooseList();
		
		return missionMapper.evaluate(questionIdList, chooseList);
	}
}
