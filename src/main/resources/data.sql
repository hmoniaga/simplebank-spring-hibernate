insert into T_Client (first_name, last_name, status) values ('Bruce','Wayne','active');
insert into T_Client (first_name, last_name, status) values ('Robin','','active');
insert into T_Client (first_name, last_name, status) values ('Peter','Parker','active');
insert into T_Client (first_name, last_name, status) values ('Thor','Odinson','inactive');

insert into T_Account (client_id, name, balance) values (1,'Wayne Enterprise Account', 100000000.12);
insert into T_Account (client_id, name, balance) values (1,'Wayne Corp Account 1', 2000000.2);
insert into T_Account (client_id, name, balance) values (1,'Wayne Corp Account 2', 3000000.12);
insert into T_Account (client_id, name, balance) values (2,'Robin Primary Account', 500000.0);
insert into T_Account (client_id, name, balance) values (2,'Robin Hidden Account', 510000.2);
insert into T_Account (client_id, name, balance) values (3,'Peter Student Fund', 100);
