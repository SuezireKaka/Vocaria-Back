create table t_educate(
	account			varchar(30),
	word			varchar(255),
	viewcnt			int
);
create index idx_educated_account on t_educate(account);
create index idx_educated_word on t_educate(word);