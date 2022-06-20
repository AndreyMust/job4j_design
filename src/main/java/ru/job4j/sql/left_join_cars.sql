--1. Создать Таблицы -   Кузов, Двигатель, Коробка передач.

create table body(
id serial primary key,
type varchar(255)
);

create table engine(
id serial primary key,
type varchar(255)
);

create table transmission(
id serial primary key,
type varchar(255)
);

--2. Создать таблицу Машина.

create table car(
id serial primary key,
name varchar(255),
body_type int references body(id),
engine_type int references engine(id),
transmission_type int references transmission(id)
);

--3. Заполнить таблицы через insert.
insert into body(type)
values ('sedan'), ('coupe'), ('wagon'), ('hatchback'), ('crossover'), ('off-roader')
;
insert into engine(type)
values('petrol'), ('diesel'), ('hybrid'), ('electro'), ('jet')
;
insert into transmission(type) values ('automatic'), ('manual'), ('CVT')
;

insert into car(name, body_type, engine_type, transmission_type)
values
('Car1', 1, 1, 1),
('Car2', 2, 2, 2),
('Car3', 3, 3, null)
;

--Вывести список всех машин и все привязанные к ним детали.
--Нужно учесть, что каких-то деталей машина может и не содержать.

select c.name, b.type "body_type", e.type "engine_type", t.type "transmission_type"
from car c
left join body b on c.body_type = b.id
left join engine e on c.engine_type = e.id
left join transmission t on c.transmission_type = t.id
;

-- Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине,
--кузова, двигатели, коробки передач.
select type, c.name
from body b
left join car c on c.body_type = b.id
where c.body_type is null
;

select type, c.name
from transmission t
left join car c on c.transmission_type = t.id
where c.transmission_type is null
;

select type, c.name
from engine e
left join car c on c.engine_type = e.id
where c.engine_type is null
;
