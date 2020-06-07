create database network_disk_user;

create table user_authentication (
    id serial primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    unique(username)
);