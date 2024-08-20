create table t_educate(
	account			varchar(30),
	chapter			char(4),
	viewcnt			int,
	primary key (account, chapter)
);