create table rel_lecture_voca (
	lecture		char(4),
	pos			int,
	voca		char(4)
);
create index idx_lecture_pos on rel_lecture_voca(lecture, pos);