package www.voca.ria.mission.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.TimeEntity;
import www.voca.ria.party.model.AccountVO;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionVO extends TimeEntity {
	private List<ScorePieceVO> scorePieceList;
	
	private AccountVO maker;
	
	private boolean isViewed;
	private boolean isComplete;
}
