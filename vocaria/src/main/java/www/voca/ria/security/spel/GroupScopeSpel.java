package www.voca.ria.security.spel;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.service.PartyService;

@Component
public class GroupScopeSpel extends CommonSpel {
	@Autowired
	private PartyService partyService;
	
	public boolean isTeachableChain(Authentication authentication,
			String accountChain) {
		ActVO teachableAct = new ActVO("SM");
		
		List<String> possibleProviderIds = authentication.getAuthorities().stream()
				.map(auth -> calcRole(auth))
				.filter(role -> role.getAllowedActList().contains(teachableAct))
				.map(role -> role.getProvider().getId())
				.collect(Collectors.toList());
		
		String[] accountArray = accountChain.split(AccountVO.CHAIN_SEPERATOR);
		
		List<AccountVO> studentList = partyService.listStudents(accountArray);
		
		return studentList.stream()
				.allMatch(account -> account.getRoleList().stream()
						.anyMatch(role -> possibleProviderIds.contains(
								role.getProvider().getId())));
	}
}
