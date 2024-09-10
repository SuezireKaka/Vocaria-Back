package www.voca.ria.party.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.GroupVO;
import www.voca.ria.party.model.PersonVO;
import www.voca.ria.party.model.role.ActVO;
import www.voca.ria.party.model.role.RoleVO;

@Mapper
public interface PartyMapper extends GeneralMapper {
	
	public List<GroupVO> listAllGroup(@Param("page") PageDTO page);
	public long countAllGroup();
	
	public List<AccountVO> listAllAccount(@Param("groupId") String groupId,
			@Param("page") PageDTO page);
	public long countAllAccount(@Param("groupId") String groupId);
	
	public List<RoleVO> listAllAnonymRoles();
	
	public List<AccountVO> listStudents(@Param("accountArray") String[] accountArray);
	
	public List<RoleVO> listAllDefaultRolesOf(@Param("group") GroupVO group);
	
	public List<ActVO> listAllAct();

	
	public GroupVO getGroupById(String groupId);
	
	public AccountVO getAccountById(String id);
	
	public RoleVO getRoleByProviderAndName(String providerId, String name);
	
	public ActVO getActByCode(String code);
	

	public boolean isValidLoginId(String loginId);
	
	public boolean isValidNick(String nick);
	
	public boolean isValidGroupName(String groupName);
	
	
	public int createPerson(@Param("person") PersonVO person);
	
	public int createAccount(@Param("account") AccountVO account);
	
	public int createGroup(@Param("group") GroupVO group);
	
	
	public int grantRolesToUser(@Param("account") AccountVO account,
			@Param("rolesList") List<RoleVO> rolesList);
	
	public int grantGroupManager(@Param("account") AccountVO account,
			@Param("group") GroupVO group);
	
	
	public int depriveRolesFromUser(@Param("account") AccountVO account,
			@Param("groupId") String groupId);
	
}
