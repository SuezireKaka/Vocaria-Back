create table rel_mission_question (
	mission		char(4),
	question	char(4),
	answer		tinyint
);
create index idx_mission on rel_mission_question(mission);
create index idx_question on rel_mission_question(question);