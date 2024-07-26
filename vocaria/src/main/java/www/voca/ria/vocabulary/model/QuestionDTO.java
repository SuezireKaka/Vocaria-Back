package www.voca.ria.vocabulary.model;

import java.util.List;
import java.util.Random;

import lombok.Getter;

@Getter
public class QuestionDTO {
	private int answer;
	private List<WordVO> choiseList;
	
	public QuestionDTO(List<WordVO> wordList) {
		this.answer = new Random().nextInt(wordList.size());
		this.choiseList = wordList;
	}
}
