package www.voca.ria.vocabulary.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.Entity;
import www.voca.ria.mission.model.QuestionVO;

@Getter
@NoArgsConstructor
public class ChapterVO extends Entity {
	private String name;
	private int questionNumber;
	private List<QuestionVO> questionList;
}
