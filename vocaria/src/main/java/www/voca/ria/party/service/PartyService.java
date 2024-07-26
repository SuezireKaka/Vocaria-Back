package www.voca.ria.party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import www.voca.ria.party.mapper.PartyMapper;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.PersonVO;
import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.model.role.RoleVO;


@Service
public class PartyService implements UserDetailsService {
	@Autowired(required = false)
	protected PartyMapper partyMapper;

	@Autowired(required = false)
	private PasswordEncoder pswdEnc;
	
	public AccountVO findById(String id) {
		AccountVO res = partyMapper.findById(id);
	
		return res;
	}
	
	public AccountVO findWriterByWorkIdFrom(String id, String table) {
		return partyMapper.findWriterByWorkIdFrom(id, table);
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
	
	public int createPerson(PersonVO person) {
		return partyMapper.createPerson(person);
	}
	
	public int updateStatus(String memberId, String role) {
		return partyMapper.updateStatus(memberId, role);
	}
	
	public int reRole(String memberId, String loginResultCode) {
		return partyMapper.reRole(memberId, loginResultCode);
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return partyMapper.findById(id);
	}

	public int deleteMember(String id) {
		return partyMapper.deleteMember(id);
	}

}
