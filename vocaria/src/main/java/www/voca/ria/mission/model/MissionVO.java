package www.voca.ria.mission.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.voca.ria.framework.model.entity.TimeEntity;
import www.voca.ria.party.model.AccountVO;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MissionVO extends TimeEntity {
	private List<ScorePieceVO> scorePieceList;
	
	private AccountVO maker;
	
	private boolean isViewed;
	private boolean isComplete;
}
