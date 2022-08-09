CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

/*
1. В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.*/

select p.name, p.company_id, c.name 
from company as c
join person as p on p.company_id = c.id
where company_id != 5
;

/*
2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
(нужно учесть, что таких компаний может быть несколько). */

select c.name as company_name, count(p.id) as empl_count
from company as c
join person as p on p.company_id = c.id
group by c.name
having count(p.name) = 
(
	select count(company_id) from person
	group by company_id
	order by count(company_id) desc
	limit 1
);
