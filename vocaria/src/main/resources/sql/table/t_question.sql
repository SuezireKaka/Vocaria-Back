create table t_question(
	id				char(4) primary key,
	account			varchar(30),
	time			date default curdate(),
	choise_num		int,
	answer			int
);
create index idx_account_time on t_question(account, time);