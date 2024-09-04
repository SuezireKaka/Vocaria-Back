package www.voca.ria.security.spel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.service.PartyService;

@Component
public class ActScopeSpel extends CommonSpel {
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
}
