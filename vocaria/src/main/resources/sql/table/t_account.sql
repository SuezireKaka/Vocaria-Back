create table t_account(
	id				varchar(30) primary key,
	pswd			varchar(255),
	nick			varchar(30),
	intro			varchar(1000),
	owner			char(4),
	reg_dt			timestamp default current_timestamp(),
	upt_dt			timestamp default current_timestamp() on update current_timestamp()
);
create index idx_owner on t_account(owner);