create table productsTwo (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into productsTwo (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_22', 'producer_22', 55, 332);
select * from productsTwo;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into productsTwo (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_one', 'producer_one', 5, 150);

select * from productsTwo;

create or replace function f_delete_data(i_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        delete from productsTwo 
		where id = i_id;        
    end;
$$;

select f_delete_data(2);

select * from productsTwo;

create or replace procedure delete_data(i_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from productsTwo 
		where id = i_id;        
    END
$$;

call delete_data(1);

select * from productsTwo;
