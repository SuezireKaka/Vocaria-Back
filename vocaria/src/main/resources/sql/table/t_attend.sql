create table t_attend(
	account			varchar(30),
	groupid			char(4),
	time			date default curdate()
);
create unique index uniq_attend on t_attend(account, groupid, time);