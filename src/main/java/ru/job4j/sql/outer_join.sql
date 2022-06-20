--1. Создать 2 таблицы и заполнить их начальными данными

create table departments(
id serial primary key,
dep_name varchar(200)
);
insert into departments (dep_name) values ('TOP_MANAGER'), ('DEVELOP'), ('SALE');

create table employees (
id serial primary key,
emp_name varchar(200),
department_id int references departments(id)
);

insert into employees(emp_name, department_id) values
    ('Maxim', 1), ('Kirill', 1), ('Mike', 1), ('Olga', 1),
    ('Maria', 2), ('Mark', 2), ('Timur', 2), ('Petr', 2),
    ('Jane', 3), ('Jon', 3), ('Kate', 3), ('David', 3);

insert into employees (n_name) values ('Buratino');

--2. Выполнить запросы с left, rigth, full, cross соединениями

select e.emp_name, d.dep_name
from employees e
left join departments d on department_id = d.id;

select e.emp_name, d.dep_name
from employees e
right join departments d on department_id = d.id;

select e.emp_name, d.dep_name
from employees e
cross join departments d;

--3. Используя left join найти департаменты, у которых нет работников
select d.dep_name depertment
from departments d
left join employees e on department_id = d.id
where e.emp_name is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат
--(порядок вывода колонок в эти запросах также должен быть идентичный).

select e.emp_name, d.dep_name
from departments d
left join employees e on department_id = d.id
where e.emp_name is not null;

select e.emp_name worker, d.dep_name
from departments d
right join employees e on department_id = d.id
where d.dep_name is not null;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join
--составить все возможные разнополые пары
create table teens(
id serial primary key,
name varchar(100),
gender varchar(1)
);

insert into teens(name, gender) values
('Anna', 'w'),
('Maja', 'w'),
('Marina', 'w'),
('Sveta', 'w'),
('Andrew', 'm'),
('Maxim', 'm'),
('Petr', 'm')
;

select t1.name, t1.gender, t2.name, t2.gender
from teens t1
cross join teens t2
where t1.gender <> t2.gender
;