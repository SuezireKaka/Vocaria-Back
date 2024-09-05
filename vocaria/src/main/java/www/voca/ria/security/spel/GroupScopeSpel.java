package www.voca.ria.security.spel;

import java.util.ArrayList;
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
	
	private List<AccountVO> imsiList = new ArrayList<>();
	
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
		
		List<AccountVO> filteredStudentList = studentList.stream()
				.filter(account -> account.getRoleList().stream()
						.anyMatch(role -> possibleProviderIds.contains(
								role.getProvider().getId())))
				.collect(Collectors.toList());
		
		this.imsiList = filteredStudentList;
		
		return filteredStudentList.size() > 0;
	}
	
	public List<AccountVO> getImsiResult() {
		List<AccountVO> result = new ArrayList<>(this.imsiList);
		
		clearImsi();
		
		return result;
	}
	
	public void clearImsi() {
		imsiList.clear();
	}
}
