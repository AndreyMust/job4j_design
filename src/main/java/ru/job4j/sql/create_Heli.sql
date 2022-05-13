create table helicopter (
    id serial primary key,
    brande text,
	buldDate date,
	enginePower int,
	mainRotor float
);

insert into helicopter (brande, buldDate, enginePower, mainRotor)
values ('Ми-8', '1960-01-01', 1510, 21.9);

select * from helicopter;

update helicopter set
enginePower = 1520;

delete from helicopter;


