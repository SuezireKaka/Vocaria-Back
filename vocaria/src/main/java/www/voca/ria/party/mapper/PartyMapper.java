package www.voca.ria.party.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.GroupVO;
import www.voca.ria.party.model.PersonVO;
import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.model.role.RoleVO;

@Mapper
public interface PartyMapper extends GeneralMapper {
	public List<RoleVO> listAllAnonymRoles();
	public List<RoleVO> listAllDefaultRolesOf(@Param("group") GroupVO group);

	public AccountVO getAccountById(String id);
	public RoleVO getRoleByProviderAndName(String providerId, String name);
	public ActVO getActByCode(String code);

	public boolean isValidLoginId(String loginId);
	public boolean isValidNick(String nick);
	
	public int createPerson(@Param("person") PersonVO person);
	public int createAccount(@Param("account") AccountVO account);
	
	public int grantRolesToUser(@Param("account") AccountVO account,
			@Param("rolesList") List<RoleVO> rolesList);
}
