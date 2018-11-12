--В системе заданы таблицы
--product(id, name, type_id, expired_date, price)
--type(id, name)
--Задание.
--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT *FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT *FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'Мороженое';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT *FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE p.expired_date BETWEEN '2018-12-01' AND '2018-12-31';

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT *FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE p.price = (SELECT max(p.price) FROM product as p);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, COUNT(*) FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
GROUP BY t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT *FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name IN('Сыр', 'Молоко');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(*)<10;

--8. Вывести все продукты и их тип.
SELECT p.name,t.name FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id;