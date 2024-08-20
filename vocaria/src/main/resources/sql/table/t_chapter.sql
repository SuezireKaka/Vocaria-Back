create table t_chapter (
	id			char(4) primary key,
	voca		char(4),
	pos			int,
	name		varchar(255)
);
create index idx_voca_pos on t_chapter(voca, pos);