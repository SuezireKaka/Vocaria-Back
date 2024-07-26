package www.voca.ria.vocabulary.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class QuestionDTO {
	private String question;
	private List<String> choiseList;
	
	public QuestionDTO(List<WordVO> wordList) {
		this.question = wordList.get(0).getMeaning();

		this.choiseList = wordList.stream()
				.map(WordVO::getWord)
				.collect(Collectors.toList());
		Collections.shuffle(this.choiseList);
	}
}
