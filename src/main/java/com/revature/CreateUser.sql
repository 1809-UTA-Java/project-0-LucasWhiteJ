--Create and user
create user &1 identified by &2 default tablespace users temporary tablespace temp quota 10m on users;

--Grant permissions to user
grant connect, resource, create session, create table, create view to &1;