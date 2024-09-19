--liquibase formatted sql

--changeset ChiniakinD:1
create table if not exists holiday
(
    id    serial primary key,
    day   int,
    month int
);

