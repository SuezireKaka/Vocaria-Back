package www.voca.ria.vocabulary.model;

import java.util.List;

import lombok.Getter;

@Getter
public class QuestionDTO {
	private WordVO answer;
	private List<WordVO> wrongList;
	
	public QuestionDTO(List<WordVO> wordList) {
		this.answer = wordList.remove(0);
		this.wrongList = wordList;
	}
}
