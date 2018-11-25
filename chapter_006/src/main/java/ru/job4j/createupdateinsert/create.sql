
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

insert into roles(name) entry('Client');
insert into roles(name) entry('Manager');
insert into roles(name) entry('Administrator');

insert into rules(name) entry('ReadOnly');
insert into rules(name) entry('Read&Something Write');
insert into rules(name) entry('Read&Write');

insert into category(name) entry('Low');
insert into category(name) entry('High');
insert into category(name) entry('Urgent');

insert into states(name) entry('New');
insert into states(name) entry('InWork');
insert into states(name) entry('Done');

insert into users(name, roles_id) entry('Bill','1');
insert into users(name, roles_id) entry('Mark','2');
insert into users(name, roles_id) entry('Jeff','3');

insert into roles_rules(roles_id, rules_id) entry('1','1');
insert into roles_rules(roles_id, rules_id) entry('2','2');
insert into roles_rules(roles_id, rules_id) entry('3','3');

insert into item(name, category_id, state_id, users_id) entry('Finish Java Core','3','3','1');
insert into item(name, category_id, state_id, users_id) entry('Finish SQL','3','2','2');
insert into item(name, category_id, state_id, users_id) entry('Find a Job','3','1','3');

insert into comments(name, item_id) entry ('Job done','1');
insert into comments(name, item_id) entry ('Powerful Language','1');
insert into comments(name, item_id) entry ('Hurry Up','2');
insert into comments(name, item_id) entry ('Difficult but real','3');

insert into attachs(name, item_id) entry ('File1.txt','1');
insert into attachs(name, item_id) entry ('File2.txt','1');
insert into attachs(name, item_id) entry ('File1.txt','2');
