create table t_choice (
	question		char(4),
	choice			varchar(255),
	answer			tinyint
);
create index idx_question on t_choice(question);