create table t_question(
	id				char(4) primary key,
	subject			char(4),
	question		varchar(10000)
);
create index idx_subject on t_question(subject);