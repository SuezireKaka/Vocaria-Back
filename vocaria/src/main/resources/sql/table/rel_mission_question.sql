create table rel_mission_question (
	mission		char(4),
	pos			int,
	question	char(4),
	answer		tinyint
);
create index idx_mission_pos on rel_mission_question(mission, pos);
create index idx_question on rel_mission_question(question);