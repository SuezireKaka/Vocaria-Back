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
import www.voca.ria.party.model.SignUpDto;
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
	
	public int manageMember(SignUpDto signUpRequest) {
		PersonVO member = PersonVO.builder()
				.name(signUpRequest.getName())
				.birthDate(signUpRequest.getBirth())
				.build();
		
		// 중복검사 어케 할 예정인?
		
		AccountVO account = AccountVO.builder()
				.id(signUpRequest.getLoginId())
				.passWord(pswdEnc.encode(signUpRequest.getRawPassword()))
				.nick(signUpRequest.getNick())
				.introduction(signUpRequest.getIntroduce())
				.build();
		
		return 0;
		//partyMapper.createPerson(member)
			//	& partyMapper.createAccount(account);
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return partyMapper.findById(id);
	}

}
