create table if not exists user (
	user_name varchar(20) primary key,
	password	varchar(100),
	email	varchar(100),
	name	varchar(100),
	version	bigint(20),
	enabled boolean,
	created_by varchar(20),
	created_date date,
	last_modified_by varchar(20),
	last_modified_date date
);