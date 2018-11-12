
--Создаем базу данных
CREATE DATABASE mydb;

-- Создаем таблицы
create table roles(
	id serial primary key,
	name varchar(2000)
);

create table rules(
	id serial primary key,
	name varchar(2000)
);

create table users(
	id serial primary key,
	name varchar(2000),
	roles_id int references roles(id)
);

create table states(
	id serial primary key,
	name varchar(2000)
);

create table category(
	id serial primary key,
	name varchar(2000)
);

create table item(
	id serial primary key,
	name varchar(2000),
	category_id int references category(id),
	state_id int references states(id),
	users_id int references users(id) UNIQUE
);

create table comments(
	id serial primary key,
	name varchar(2000),
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name varchar(2000),
	item_id int references item(id)
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

-- Заполняем начальные данные

insert into roles(name) values('Client');
insert into roles(name) values('Manager');
insert into roles(name) values('Administrator');

insert into rules(name) values('ReadOnly');
insert into rules(name) values('Read&Something Write');
insert into rules(name) values('Read&Write');

insert into category(name) values('Low');
insert into category(name) values('High');
insert into category(name) values('Urgent');

insert into states(name) values('New');
insert into states(name) values('InWork');
insert into states(name) values('Done');

insert into users(name, roles_id) values('Bill','1');
insert into users(name, roles_id) values('Mark','2');
insert into users(name, roles_id) values('Jeff','3');

insert into roles_rules(roles_id, rules_id) values('1','1');
insert into roles_rules(roles_id, rules_id) values('2','2');
insert into roles_rules(roles_id, rules_id) values('3','3');

insert into item(name, category_id, state_id, users_id) values('Finish Java Core','3','3','1');
insert into item(name, category_id, state_id, users_id) values('Finish SQL','3','2','2');
insert into item(name, category_id, state_id, users_id) values('Find a Job','3','1','3');

insert into comments(name, item_id) values ('Job done','1');
insert into comments(name, item_id) values ('Powerful Language','1');
insert into comments(name, item_id) values ('Hurry Up','2');
insert into comments(name, item_id) values ('Difficult but real','3');

insert into attachs(name, item_id) values ('File1.txt','1');
insert into attachs(name, item_id) values ('File2.txt','1');
insert into attachs(name, item_id) values ('File1.txt','2');
