--Creates the Customer table
create table customer (
	custid integer primary key,
	custname varchar2(20),
	custusername varchar2(20),
	custpassword varchar2(20)
);

--Creates a sequence to auto incerment the customer id by 1
create sequence custidincrement start with 1 increment by 1;



--Creates the Employee table
create table employee (
	empid integer primary key,
	empname varchar2(20),
	empusername varchar2(20),
	emppassword varchar2(20),
	isanadmin varchar2(5)
);

--Creates a sequence to auto incerment the employee id by 1
create sequence empidincrement start with 1 increment by 1;



--Creates the Account table
create table account (
	accid integer primary key,
	name varchar2(20),
	status varchar2(7),
	balance integer,
	ownerid integer,
	coownerid integer
);

--Creates a sequence to auto incerment the account id by 1
create sequence accidincrement start with 1 increment by 1;

--Sets Account table's Foreign Keys pointing to the Customer table
alter table account add constraint fkowner
foreign key (ownerid)
references customer (custid)
on delete cascade;

alter table account add constraint fkcoowner
foreign key (coownerid)
references customer (custid)
on delete cascade;

--commits changes
commit;