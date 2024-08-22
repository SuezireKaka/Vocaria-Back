package www.voca.ria.mission.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import www.voca.ria.party.model.AccountVO;

@Getter
@Builder
public class MissionDTO {
	private AccountVO student;
	private List<MissionVO> missionList;
}
