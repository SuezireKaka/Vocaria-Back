package www.voca.ria.security.spel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.model.role.RoleVO;
import www.voca.ria.party.service.PartyService;

@Component
public class ActScopeSpel {
	@Autowired
	private PartyService partyService;
	
	public boolean isAbleToRunAny(Authentication authentication,
			String providerId, String... actArray) {
		
		List<ActVO> ableActs = authentication.getAuthorities().stream()
				.map(auth -> calcRole(auth))
				.filter(role -> role.getProvider().getId().equals(providerId))
				.map(auth -> calcRole(auth).getAllowedActList())
				.reduce(new ArrayList<>(), (a, b) -> {
					a.addAll(b);
					return a;
				});
		
		List<ActVO> requiredActs = Arrays.stream(actArray)
				.map(act -> partyService.getActByCode(act))
				.collect(Collectors.toList());
		
		ableActs.retainAll(requiredActs);
		
		return ableActs.size() > 0;
	}
	
	private RoleVO calcRole(GrantedAuthority auth) {
		String[] parsedAuth = auth.getAuthority().split(RoleVO.AUTHORITY_SEPERATOR);
		return partyService.getRoleByProviderAndName(parsedAuth[0], parsedAuth[1]);
	}
}
