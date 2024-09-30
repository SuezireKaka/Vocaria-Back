package www.voca.ria.mission.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.vocabulary.model.QuestionVO;

@Getter
@NoArgsConstructor
public class ScorePieceVO {
	private QuestionVO question;
	private boolean isAnswer;
}
