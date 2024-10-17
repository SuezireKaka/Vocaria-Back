create table t_mission(
	id				char(4) primary key,
	tester			varchar(255),
	maker			varchar(255),
	reg_dt			timestamp default current_timestamp(),
	upt_dt			timestamp default current_timestamp() on update current_timestamp(),
	xpr_dt			timestamp,
	viewed			tinyint
);
create index idx_reg_dt on t_mission(reg_dt);
create index idx_maker on t_mission(maker);
create index idx_tester on t_mission(tester);