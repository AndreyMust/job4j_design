
-- many-to-one
create table animal_type(
    id serial primary key,
    name varchar(255)
);

create table pets(
    id serial primary key,
    name varchar(255),
    type_id int references animal_type(id)
);

insert into animal_type(name) values ('cat');
insert into animal_type(name) values ('dog');
insert into pets(name, type_id) VALUES ('Murzik', 1);

select * from pets;
select * from animal_type where id in (select type_id from pets);

-- one-to-one
create table inn(
    id serial primary key,
    number int unique
);

create table people(
    id serial primary key,
    name varchar(255),
    inn_id int references inn(id) unique
);

insert into inn(number) values (11111111);
insert into people(name, inn_id) values ('Ivan',1);
select * from  people;

--many-to-many
create table pet_owner(
    id serial primary key,
    pet_id int references pets(id),
    people_id int references people(id)
);

insert into pet_owner(pet_id, people_id) values (1,1);
select * from pet_owner;