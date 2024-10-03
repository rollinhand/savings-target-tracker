create table STT_USERS
(
    id        char(64) primary key,
    firstname varchar(255),
    lastname  varchar(255),
    birthdate date,
    email     varchar(255)
);