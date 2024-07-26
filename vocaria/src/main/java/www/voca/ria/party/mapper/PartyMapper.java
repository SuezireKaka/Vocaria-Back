package www.voca.ria.party.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PagingDTO;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.PersonVO;
import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.model.role.RoleVO;

@Mapper
public interface PartyMapper extends GeneralMapper {
	public List<AccountVO> listAllAccount(@Param("ownerId") String ownerId, @Param("paging") PagingDTO paging);
	
	public AccountVO findById(String id);
	
	public AccountVO findWriterByWorkIdFrom(String id, String table);
	
	public boolean isValidLoginId(String loginId);
	public boolean isValidNick(String nick);
	public boolean checkUniqueVal(String key, String val);
	
	public RoleVO getRoleByProviderAndName(String providerId, String name);
	
	public ActVO getActByCode(String code);

	public int createPerson(PersonVO person);
	public int createAccount(AccountVO account);
	public int createRole(@Param("account") AccountVO account, @Param("role") RoleVO role);
	
	public int updatePerson(@Param("person") PersonVO person);
	public int updateAccount(@Param("account") AccountVO account);

	public int updateStatus(String memberId, String loginResultCode);
	public int reRole(String memberId, String role);
	public int deleteMember(String id);
	public int deleteAllCpOf(String id);

	

	
	
	
}
