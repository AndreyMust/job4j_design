create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

insert into devices(name, price)
values('HDD', 6500), ('RAM_4GB', 4200), ('Keyboard', 1400),
('RAM_16GB', 12200), ('GPU', 54000)

insert into devices(name, price)
values('HDD', 6700), ('RAM_4GB', 4300), ('Keyboard', 1550),
('RAM_16GB', 33500), ('GPU', 62100)


create table people(
    id serial primary key,
    name varchar(255)
);

insert into people(name)
values ('Anna'), ('Ivan'), ('Petr'), ('Albert')

select * from people
select * from devices

--средняя стоимость устройств
select name, avg(price)
from devices
group by name


--средняя стоимость устройств больше 5000
select name, avg(price)
from devices
group by name
having avg(price)>5000

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices_people(device_id, people_id)
values (1,1), (1,2), (1,3), (1,4),
(2,1), (2,2), (2,3),
(6,1), (7,2), (8,3), (9,4),
(4,1), (3,2), (2,3), (8,4)

--средняя стоимость всех устройств у пользователя
select p.name, avg(d.price)
from people as p
join devices_people dp on dp.people_id = p.id
join devices d on d.id = dp.device_id
group by p.name

