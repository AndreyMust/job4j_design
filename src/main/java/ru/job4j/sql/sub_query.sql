CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers(first_name, last_name, age, country) VALUES 
('Иванов', 'Иван',33, 'РФ'),
('Петров', 'Петр',25, 'РФ'),
('Сидоров1', 'Коля',25, 'РФ'),
('Сидоров2', 'Альберт',38, 'РФ'),
('Сидоров3', 'Костя',45, 'РФ')
;

--Выполните запрос, который вернет список клиентов, возраст которых является минимальным.
select * from customers
where age = (select min(age) from customers)
;

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO orders(amount, customer_id) VALUES 
(5,1), (10,2), (7,2), (15,1)
;

--Необходимо выполнить запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа.
select * from customers
where id not in (select customer_id from orders)
;