package www.voca.ria.vocabulary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.mapper.VocaMapper;
import www.voca.ria.vocabulary.model.QuestionDTO;
import www.voca.ria.vocabulary.model.WordVO;

@Service
public class VocaService {
	@Autowired
	private VocaMapper vocaMapper;

	public QuestionDTO getRandomQuestion(int choiceNum) {
		List<WordVO> wordList = vocaMapper.listRandomWordsOfNumber(choiceNum);
		
		return new QuestionDTO(wordList);
	}
	
	public int setupTodayMission(AccountVO student) {
		int todayMissionNum = QuestionDTO.QUESTION_NUM_PER_EXAM
				* QuestionDTO.CHOISE_NUM;
		
		return vocaMapper.setupTodayWords(student, todayMissionNum);
	}
}
