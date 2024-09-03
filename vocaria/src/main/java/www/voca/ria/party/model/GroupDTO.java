package www.voca.ria.party.model;

import lombok.Getter;

@Getter
public class GroupDTO {
	private GroupVO group;
	private boolean isJoined;
	
	public GroupDTO(GroupVO group, AccountVO client) {
		this.group = group;
		
		this.isJoined = client.isJoined(group.getId());
	}
}
