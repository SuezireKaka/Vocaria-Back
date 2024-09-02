DELIMITER $$
CREATE OR REPLACE PROCEDURE GRANT_GROUP_MANAGER(accountId varchar(255), groupId char(4))
BEGIN
	DECLARE manager_role_id char(4);
	DECLARE member_role_id char(4);

	select NEXT_PK('s_role') into manager_role_id;
	select NEXT_PK('s_role') into member_role_id;
	
	insert into t_role(id, provider, name, info, dflt)
	values (manager_role_id, groupId, 'manager', '해당 그룹의 관리자(기본 생성됨)', 0),
	(member_role_id, groupId, 'member', '해당 그룹의 멤버(기본 생성됨)', 1);

	insert into rel_role_act(role, act)
	values (manager_role_id, 'SM'),
	(manager_role_id, 'ST'),
	(manager_role_id, 'TM'),
	(member_role_id, 'PS');

	insert into rel_account_role(account, role)
	values (accountId, manager_role_id),
	(accountId, member_role_id);
END;
$$
DELIMITER ;