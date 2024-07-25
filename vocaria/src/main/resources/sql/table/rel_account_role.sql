create table rel_account_role(
	account		varchar(255),
	role		char(4)
);
create index idx_party on rel_party_role(party);