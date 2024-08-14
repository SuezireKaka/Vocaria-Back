create table t_educate(
	account			varchar(30),
	word			varchar(255),
	viewcnt			int,
	primary key (account, word)
);