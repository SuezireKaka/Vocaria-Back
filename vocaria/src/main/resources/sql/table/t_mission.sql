create table t_mission(
	id				char(4) primary key,
	tester			varchar(255),
	maker			varchar(255),
	time			date default curdate(),
	viewed			tinyint,
	complete		tinyint
);
create index idx_account_time on t_mission(tester, time);