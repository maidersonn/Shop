create table if not exists user_table (
    id serial NOT NULL PRIMARY KEY,
    username varchar(20),
    password varchar(100)
)