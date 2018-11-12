--Нужно написать SQL скрипты:
--Создать структур данных в базе.
--Таблицы: Кузов. Двигатель, Коробка передач.
--Создать структуру Машина. Машина не может существовать без данных из п.1.
--Заполнить таблицы через insert.
--Создать SQL запросы:
--1. Вывести список всех машин и все привязанные к ним детали.
--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.


--  Создаем таблицы
CREATE TABLE car_body(
	id serial PRIMARY KEY,
	name VARCHAR(2000)
);

CREATE TABLE engine(
	id serial PRIMARY KEY,
	name VARCHAR(2000)
);

CREATE TABLE transmission(
	id serial PRIMARY KEY,
	name VARCHAR(2000)
);

CREATE TABLE car(
	id serial PRIMARY KEY,
	name VARCHAR(2000),
	car_body_id int REFERENCES car_body(id),
	engine_id int REFERENCES engine(id),
	transmission_id int REFERENCES transmission(id)
);

--Заполняем таблицы
INSERT INTO car_body(name) VALUES ('Седан');
INSERT INTO car_body(name) VALUES ('Универсал');
INSERT INTO car_body(name) VALUES ('Хетчбэк');
INSERT INTO car_body(name) VALUES ('Минивэн');

INSERT INTO engine(name) VALUES ('1,6 Бензин');
INSERT INTO engine(name) VALUES ('2,0 Бензин');
INSERT INTO engine(name) VALUES ('2,3 Дизель');
INSERT INTO engine(name) VALUES ('1,4 Турбо Бензин');

INSERT INTO transmission(name) VALUES ('5-ступ. ручная');
INSERT INTO transmission(name) VALUES ('7-ступ. робот');
INSERT INTO transmission(name) VALUES ('6-ступ. автомат');
INSERT INTO transmission(name) VALUES ('DSG-7');

INSERT INTO car(name, car_body_id, engine_id, transmission_id) VALUES ('AUDI',2,2,4);
INSERT INTO car(name, car_body_id, engine_id, transmission_id) VALUES ('Лада седан баклажан',1,1,1);
INSERT INTO car(name, car_body_id, engine_id, transmission_id) VALUES ('BMW',1,2,3);


--1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.name,cb.name,e.name,t.name FROM car AS c
LEFT OUTER JOIN car_body AS cb ON c.car_body_id=cb.id
LEFT OUTER JOIN engine AS e ON c.engine_id=e.id
LEFT OUTER JOIN transmission AS t ON c.transmission_id=t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT cb.name FROM car AS c
RIGHT OUTER JOIN car_body AS cb ON c.car_body_id=cb.id
WHERE c.id IS NULL;

SELECT e.name FROM car AS c
RIGHT OUTER JOIN engine AS e ON c.engine_id=e.id
WHERE c.id IS NULL;

SELECT t.name FROM car AS c
RIGHT OUTER JOIN transmission AS t ON c.transmission_id=t.id
WHERE c.id IS NULL;