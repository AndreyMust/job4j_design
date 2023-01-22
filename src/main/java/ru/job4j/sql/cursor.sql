select * from products
;

BEGIN;
DECLARE
    cursor_products cursor for
                        select * from products;
FETCH FROM cursor_products;
FETCH LAST FROM cursor_products;

MOVE BACKWARD 5 FROM cursor_products;
MOVE BACKWARD 8 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
FETCH FIRST FROM cursor_products;
rollback;
