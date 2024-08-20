create table rel_chapter_question(
	chapter		char(4),
	pos			int,
	question	char(4)
);
create index idx_chapter_pos on rel_chapter_question(chapter, pos);