package www.voca.ria.security.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import www.voca.ria.party.service.PartyService;

@Component
public class GroupScopeSpel {
	@Autowired
	private PartyService partyService;
	
	public boolean isTeachableChain(Authentication authentication,
			String providerId, String accountChain) {
		
		
		
		return false;
	}
}
