create table rel_mission_question (
	mission		char(4),
	question	char(4)
);
create index idx_mission on rel_mission_question(mission);