package www.voca.ria.party.model.role;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GrantDTO {
	private String accountId;
	private List<RoleVO> roleList;
	
	public boolean isSecureGrant(String groupId) {
		return roleList.stream().allMatch(role ->
			role.getProvider().getId().equals(groupId));
	}
}
