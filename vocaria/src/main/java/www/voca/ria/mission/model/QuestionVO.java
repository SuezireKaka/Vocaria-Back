package www.voca.ria.mission.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.Entity;
import www.voca.ria.vocabulary.model.SubjectVO;

@Getter
@NoArgsConstructor
public class QuestionVO extends Entity {
	private SubjectVO subject;
	private String question;
	private List<String> choiceList;
	
	private int selectedChoice = 0;
}
