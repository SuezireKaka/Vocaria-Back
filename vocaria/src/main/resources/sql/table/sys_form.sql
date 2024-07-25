create table sys_form(
	purpose			varchar(255),
	pos				int,
	title			varchar(255),
	type			varchar(16),
	valid_regex		varchar(255),
	uniqe			tinyint,
	primary key(purpose, pos)
);