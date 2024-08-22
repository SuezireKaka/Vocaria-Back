package www.voca.ria.mission.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.TimeEntity;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.QuestionVO;

@Getter
@NoArgsConstructor
public class MissionVO extends TimeEntity {
	private AccountVO student;
	private QuestionVO question;
	private boolean isComplete;
}
