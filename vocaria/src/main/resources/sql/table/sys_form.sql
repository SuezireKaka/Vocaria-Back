create table sys_form(
	purpose				varchar(255),
	level				int,
	input_title			varchar(255),
	input_type			varchar(16),
	input_valid_regex	varchar(255),
	input_unique		tinyint,
	primary key(purpose, level)
);