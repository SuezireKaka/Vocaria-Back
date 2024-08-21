create table t_subscribe (
	account		varchar(255),
	voca		char(4),
	alive		tinyint default 1,
	pointer		int default 1,
	init_date	date default curdate(),
	primary key(account, voca)
);