package www.voca.ria.party.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.voca.ria.party.model.role.RoleVO;

@Getter
@SuperBuilder
@NoArgsConstructor
public class GroupVO extends PartyVO {
	public static final GroupVO JMEMO_PROXY = GroupVO.builder()
			.id("0000")
			.build();
	
	private boolean isOpen;
	private List<RoleVO> providingRoleList;
}