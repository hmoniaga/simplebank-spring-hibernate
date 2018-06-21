drop table T_Client  if exists;
drop table T_Account if exists;

create table T_Client (id integer not null auto_increment, first_name varchar(255), last_name varchar(255), status varchar(255), primary key (id));

create table T_Account (id integer not null auto_increment, client_id integer, name varchar(255), balance bigint, primary key (id));

alter table T_Account add constraint fk_account_has_client foreign key (client_id) references T_Client;
