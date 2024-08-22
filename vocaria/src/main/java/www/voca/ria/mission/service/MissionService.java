package www.voca.ria.mission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.mission.mapper.MissionMapper;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.ChapterVO;

@Service
public class MissionService {
	@Autowired
	private MissionMapper missionMapper;
	
	public ChapterVO getMission(String accountId, String dateString) {
		
		return null;
	}
	
	public int setupTodayMission(AccountVO student) {
		return missionMapper.setupTodayMission(student);
	}

	public boolean buildMissionAutomatically(List<String> parsedData) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean buildMissionDirectly(List<String> parsedData) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
