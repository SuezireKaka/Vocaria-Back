package www.voca.ria.vocabulary.model;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.Entity;

@Getter
@NoArgsConstructor
public class QuestionVO extends Entity {
	private String question;
	private List<String> choiceList;
	
	public void shuffle() {
		Collections.shuffle(this.choiceList);
	}
}
