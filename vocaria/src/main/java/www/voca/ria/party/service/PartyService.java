package www.voca.ria.party.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import www.voca.ria.framework.model.DreamPair;
import www.voca.ria.framework.model.PagingDTO;
import www.voca.ria.party.mapper.PartyMapper;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.ContactPointVO;
import www.voca.ria.party.model.OrganizationVO;
import www.voca.ria.party.model.PartyVO;
import www.voca.ria.party.model.PersonVO;
import www.voca.ria.party.model.RoleVO;
import www.voca.ria.party.model.SignUpDto;


@Service
public class PartyService implements UserDetailsService {
	@Autowired(required = false)
	protected PartyMapper partyMapper;

	@Autowired(required = false)
	private PasswordEncoder pswdEnc;
	
	/** 회사의 모든 계정 조회 */ 
	public DreamPair<List<AccountVO>, PagingDTO> listAllAccount(String ownerId, int page) {
		PagingDTO paging = new PagingDTO(page);
		List<AccountVO> listResult = partyMapper.listAllAccount(ownerId, paging);
		
		for (AccountVO account : listResult) {
			PartyVO owner = account.getOwner();
			List<ContactPointVO> contactsList = partyMapper.listAllCpOf(owner.getId());
			owner.addAllCPs(contactsList);
		}

		long dataCount = partyMapper.getFoundRows();
		paging.buildPagination(dataCount);

		return new DreamPair<List<AccountVO>, PagingDTO>(listResult, paging);
	}
	
	public AccountVO findById(String id) {
		AccountVO res = partyMapper.findById(id);
	
		return res;
	}
	
	public AccountVO findWriterByWorkIdFrom(String id, String table) {
		return partyMapper.findWriterByWorkIdFrom(id, table);
	}
	
	public boolean checkLoginId(String loginId) {
		return partyMapper.isValidLoginId(loginId);
	}
	public boolean checkNick(String nick) {
		return partyMapper.isValidNick(nick);
	}
	
	/** 회원 가입 */
	public int mngMember(SignUpDto signUpRequest) {
		// 동일인 검증 어케 하나요 ㅠㅠ
		// 모르겠고 빌더패턴이나 쓰자
		PersonVO person = PersonVO.builder()
				.name(signUpRequest.getName())
				.sex(signUpRequest.getSex())
				.birthDate(signUpRequest.getBirthDate())
				.build();
		AccountVO account = AccountVO.builder()
				.id(signUpRequest.getLoginId())
				.passWord(signUpRequest.getPassWord())
				.nick(signUpRequest.getNick())
				.introduction(signUpRequest.getIntroduction())
				.provider(OrganizationVO.BIBLARY_PROXY)
				.owner(person)
				.build();
		int cnt = 1;
		account.encodePswd(pswdEnc);
		// accountId가 비어있으면 생성 아니면 수정
		if (ObjectUtils.isEmpty(signUpRequest.getAccountId())) {
			RoleVO defaultRole = new RoleVO();
			
			cnt &= partyMapper.createPerson(person);
			cnt &= partyMapper.createAccount(account)
					& partyMapper.createRole(account, defaultRole);
			if (signUpRequest.getListContactPoint().size() > 0) {
				cnt &= partyMapper.createAllCpOf(person.getId(), signUpRequest.getListContactPoint());
			}
			return cnt;
		}
		else {
			person.setId(signUpRequest.getPartyId());
			account.setId(signUpRequest.getAccountId());
			cnt &= partyMapper.updatePerson(person)
					& partyMapper.updateAccount(account)
					& partyMapper.deleteAllCpOf(person.getId());
			if (signUpRequest.getListContactPoint().size() > 0) {
				cnt &= partyMapper.createAllCpOf(person.getId(), signUpRequest.getListContactPoint());
			}
			return cnt;
		}
		
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
