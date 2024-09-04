package www.voca.ria.security.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import www.voca.ria.party.model.role.RoleVO;
import www.voca.ria.party.service.PartyService;

@Component
public abstract class CommonSpel {
	@Autowired
	private PartyService partyService;
	
	public RoleVO calcRole(GrantedAuthority auth) {
		String[] parsedAuth = auth.getAuthority().split(RoleVO.AUTHORITY_SEPERATOR);
		return partyService.getRoleByProviderAndName(parsedAuth[0], parsedAuth[1]);
	}
}
