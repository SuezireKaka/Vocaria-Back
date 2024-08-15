package www.voca.ria.vocabulary.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionDTO {
	public static final int CHOISE_NUM = 4;
	public static final int QUESTION_NUM_PER_EXAM = 3;
	
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
