-- есть две таблицы
--CREATE TABLE company
--(
--id integer NOT NULL,
--name character varying,
--CONSTRAINT company_pkey PRIMARY KEY (id)
--);

--CREATE TABLE person
--(
--id integer NOT NULL,
--name character varying,
--company_id integer,
--CONSTRAINT person_pkey PRIMARY KEY (id)
--);


--  нужно
-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
-- 2) Select the name of the company with the maximum number of persons + number of persons in this company

-- Получить в одном запросе
-- 1) Имена всех person, не находящихся в компании id=5 с указанием их компаний
-- 2) Показать имя компании с максимумом сотрудников, с указанием количества сотрудников


-- Формируем запросы

--1)
SELECT p.name,c.name FROM person AS p
RIGHT OUTER JOIN company AS c ON p.company_id=c.id
WHERE p.company_id!='5';

--2)
SELECT c.name, COUNT(*) AS num FROM company AS c
INNER JOIN person AS p ON p.company_id=c.id
GROUP BY c.name
LIMIT 1;