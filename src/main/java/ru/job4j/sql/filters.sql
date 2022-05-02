--product(id, name, type_id, expired_date, price)
create table type
(
    id   serial primary key,
    name text
);
insert into type(name)
values ('Сыр'),('Молоко'),('Мясо')

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into product(name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, date '2022-05-10', 945),
('Сыр пармезан', 1, date '2022-05-11', 1245),
('Молоко в Деревне', 2, date '2022-06-11', 345),
('Молоко Простоквашино', 2, date '2022-06-21', 380),
('Мясо гриль', 3, date '2022-05-02', 480),
('Мясо штиль', 3, date '2022-05-12', 580),
('Мясо штик', 3, date '2022-05-13', 520)

--Написать запрос получение всех продуктов с типом "СЫР"
select p.name, t.name as 'type'
from product p
join type t on t.id = p.type_id
where t.name = 'Сыр'

insert into product(name, type_id, expired_date, price)
values ('Гравское мороженое ', 2, date '2022-08-10', 145)

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select *
from product p
where p.name like '%мороженое%'

--Написать запрос, который выводит все продукты, срок годности которых уже истек
select name, expired_date
from product p
where p.expired_date < current_date
--date '2022-05-03'

--Написать запрос, который выводит самый дорогой продукт.
select p.name
from product p
where price = (select max(price) from product);

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name
from product p
join type t on t.id = p.type_id
where t.name in ('Сыр', 'Молоко')

--Вывести все продукты и их тип.
select p.name, t.name
from product p
join type t on t.id = p.type_id

--Написать запрос, который выводит для каждого типа количество продуктов
--к нему принадлежащих. В виде имя_типа, количество
select t.name, count(p.name)
from product as p
join type as t on p.type_id = t.id
group by t.name;

Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(p.name)
from product as p
join type as t on p.type_id = t.id
group by t.name
having count(p.name) < 10;
