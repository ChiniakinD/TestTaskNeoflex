--liquibase formatted sql

--changeset ChiniakinD:2
insert into holiday (day, month)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (23, 2),
       (8, 3),
       (1, 5),
       (9, 5),
       (12, 6),
       (4, 11);