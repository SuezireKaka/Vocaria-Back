create table t_party (
	id			char(4) primary key,
	descrim		varchar(16) comment 'organi or person',
	name		varchar(255),
	birth		date
)