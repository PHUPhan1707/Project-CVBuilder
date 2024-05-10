create database cvdatasystem;
show databases;
use cvdatasystem;
create table users(username varchar(20), password varchar(20));

create table data(username varchar(20), password varchar(20));

insert into data value('admin','12345');

select*from data;