package www.voca.ria.mission.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.Entity;
import www.voca.ria.vocabulary.model.QuestionVO;

@Getter
@NoArgsConstructor
public class MissionVO extends Entity {
	private QuestionVO question;
	private boolean isComplete;
	private Date date;
}
