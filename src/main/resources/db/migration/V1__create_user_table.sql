create table if not exists users (
    id int unsigned primary key auto_increment,
    email varchar(50) not null unique,
--     password binary(40) not null,
    password varchar(100) not null,
    enable bit default 0,
    admin bit default 0
);
