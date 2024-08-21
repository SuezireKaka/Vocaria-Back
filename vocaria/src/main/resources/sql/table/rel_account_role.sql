create table rel_account_role(
	account		varchar(30),
	role		char(4)
);
create index idx_account on rel_account_role(account);