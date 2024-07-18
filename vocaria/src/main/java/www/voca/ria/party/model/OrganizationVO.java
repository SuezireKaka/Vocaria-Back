package www.voca.ria.party.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationVO extends PartyVO {
	public static String BIBLARY_ID = "0000";

	public static OrganizationVO BIBLARY_PROXY = new OrganizationVO("0000");
	
	private OrganizationVO(String id) {
		this.setId(id);
	}
}
