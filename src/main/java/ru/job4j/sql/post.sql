CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    name text,
    text text,
    link text unique,
    created timestamp
);
