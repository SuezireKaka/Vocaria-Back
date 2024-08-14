create table t_daily_mission (
	account			varchar(30),
	time			date default curdate(),
	word			varchar(255),
	hasread			tinyint default 0,
	complete		tinyint default 0,
	primary key(account, time, word)
);