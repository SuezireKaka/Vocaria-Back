create table t_voca (
	id			char(4) primary key,
	name		varchar(255),
	maker		varchar(30),
	intro		varchar(1000),
	reg_dt		timestamp default current_timestamp(),
	upt_dt		timestamp default current_timestamp() on update current_timestamp(),
	allow		char(2)
);