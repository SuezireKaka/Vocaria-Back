package www.voca.ria.mission.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.Entity;

@Getter
@NoArgsConstructor
public class ScorePieceVO extends Entity {
	private QuestionVO question;
	private boolean isAnswer;
}
