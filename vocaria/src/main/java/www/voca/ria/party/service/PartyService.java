package www.voca.ria.party.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.framework.model.structure.Pair;
import www.voca.ria.party.mapper.PartyMapper;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.GroupVO;
import www.voca.ria.party.model.PersonVO;
import www.voca.ria.party.model.SignUpDto;
import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.model.role.GrantDTO;
import www.voca.ria.party.model.role.RoleVO;


@Service
public class PartyService implements UserDetailsService {
	@Autowired
	private PartyMapper partyMapper;

	@Autowired
	private PasswordEncoder pswdEnc;
	
	public Pair<List<AccountVO>, PageDTO> listAllAccount(String groupId, int pageNum) {
		PageDTO page = new PageDTO(pageNum);
		
		List<AccountVO> accountList = partyMapper.listAllAccount(groupId, page);
		
		page.buildPagination(partyMapper.getFoundRows());
		
		return new Pair<>(accountList, page);
	}
	
	public List<ActVO> listAllAct() {
		return partyMapper.listAllAct();
	}
	
	public GroupVO getGroupById(String groupId) {
		return partyMapper.getGroupById(groupId);
	}
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return partyMapper.getAccountById(id);
	}
	
	public RoleVO getRoleByProviderAndName(String providerId, String name) {
		return partyMapper.getRoleByProviderAndName(providerId, name);
	}
	
	public ActVO getActByCode(String code) {
		return partyMapper.getActByCode(code);
	}
	
	public boolean checkLoginId(String loginId) {
		return partyMapper.isValidLoginId(loginId);
	}
	public boolean checkNick(String nick) {
		return partyMapper.isValidNick(nick);
	}
	
	public boolean checkGroupName(String groupName) {
		return partyMapper.isValidGroupName(groupName);
	}
	
	public int manageMember(SignUpDto signUpRequest) {
		PersonVO person = PersonVO.builder()
				.name(signUpRequest.getName())
				.birthDate(signUpRequest.getBirth())
				.build();
		
		// 중복검사 어케 할 예정인?
		
		AccountVO account = AccountVO.builder()
				.owner(person)
				.id(signUpRequest.getLoginId())
				.passWord(pswdEnc.encode(signUpRequest.getRawPassword()))
				.nick(signUpRequest.getNick())
				.introduction(signUpRequest.getIntroduce())
				.build();
		
		List<RoleVO> defaultRoleList =
				partyMapper.listAllDefaultRolesOf(GroupVO.VOCARIA_PROXY);
		
		return partyMapper.createPerson(person)
			& partyMapper.createAccount(account)
			& partyMapper.grantRolesToUser(account, defaultRoleList);
	}

	public int syncRole(String groupId, List<GrantDTO> grantList) {
		if (grantList.size() == 0) {
			return 0;
		}
		
		int result = 1;
		
		// 전부 초기화하고 넣는 게 더 나은 듯;
		for (GrantDTO grant : grantList) {
			AccountVO dummy = AccountVO.builder()
					.id(grant.getAccountId())
					.build();
			
			List<RoleVO> rolesList = grant.getRoleList();
			
			result += partyMapper.depriveRolesFromUser(dummy, groupId)
					+ partyMapper.grantRolesToUser(dummy, rolesList);
		}
		
		return result;
	}

}




