package www.voca.ria.mission.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.Entity;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.QuestionVO;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionVO extends Entity {
	private List<QuestionVO> questionList;
	
	private AccountVO maker;
	
	private boolean isViewed;
	private boolean isComplete;
}
