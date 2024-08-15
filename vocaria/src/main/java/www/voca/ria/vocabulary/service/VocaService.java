package www.voca.ria.vocabulary.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.framework.exception.BusinessException;
import www.voca.ria.framework.exception.ErrorCode;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.mapper.VocaMapper;
import www.voca.ria.vocabulary.model.MissionDTO;
import www.voca.ria.vocabulary.model.MissionVO;
import www.voca.ria.vocabulary.model.QuestionDTO;

@Service
public class VocaService {
	@Autowired
	private VocaMapper vocaMapper;
	
	public MissionDTO getMission(String accountId, String dateString) {
		
		List<MissionVO> missionList =
				vocaMapper.listAllMission(accountId, dateString);
		
		try {
			return new MissionDTO(missionList, dateString);
		}
		catch (ParseException e) {
			throw new BusinessException("날짜 형식을 다시 확인해주세요", ErrorCode.STRANGE_DATE);
		}
	}
	
	public int setupTodayMission(AccountVO student) {
		
		int todayMissionNum = QuestionDTO.QUESTION_NUM_PER_EXAM
				* QuestionDTO.CHOISE_NUM;
		
		return vocaMapper.setupTodayWords(student, todayMissionNum);
	}
}
