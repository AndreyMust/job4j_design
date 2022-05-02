create table users(
    id serial primary key,
    fio varchar(255),
    age int,
	adress varchar(255)
);

insert into users(fio, age, adress)
values('Иванов',33, 'Москва 15'), ('Петров',31, 'Москва 44'),
('Сидоров',41, 'Питер 34')

create table orders(
    id serial primary key,
    description varchar(255),
	count int,
	good_id int,
    user_id int references users(id)
);

insert into orders(description, count, good_id, user_id)
values('Батарейки', 2, 5321, 1),('Очки', 1, 5336, 1),
('Батарейки', 5, 5321, 3),('Очки', 1, 5336, 2)


--отобразить Пользователей, которые заказывали Очки
select u.fio
from users as u
join orders as o on o.user_id = u.id
where o.description = 'Очки'

--отобразить все заказы Сидорова
select o.description, o.count
from users as u
join orders as o on o.user_id = u.id
where u.fio = 'Сидоров'
