CREATE TABLE groop
(
    id     BIGSERIAL primary key,
    number varchar(64) not null unique
);

INSERT INTO groop(number)
VALUES ('106519'),
       ('106516'),
       ('106629');

CREATE TABLE student
(
    id         bigserial primary key,
    first_name varchar(128),
    last_name  varchar(128),
    groop_id   bigint references groop (id) on delete cascade
);

INSERT INTO student(first_name, last_name, groop_id)
VALUES ('Vladimir','Begliak', 1),
       ('Alex','Popov', 2),
       ('Michael','Jordan', 2),
       ('Lebron','James', 3);

CREATE TABLE lection
(
    id       bigserial primary key,
    name     varchar,
    day      varchar,
    groop_id bigint references lection (id) on delete cascade
);

INSERT INTO lection(name, day, groop_id)
VALUES ('Mathematics', 'MONDAY', 1),
       ('Physics', 'MONDAY', 1),

       ('Mathematics', 'TUESDAY', 1),
       ('Mathematics', 'TUESDAY', 1),

       ('Chemistry', 'WEDNESDAY', 1),
       ('Physical culture', 'WEDNESDAY', 1),

       ('Mathematics', 'FRIDAY', 1),
       ('Russian literature', 'FRIDAY', 1),
       ('Physics', 'FRIDAY', 1),

       ('Belarusian language', 'MONDAY', 2),
       ('Painting', 'MONDAY', 2),

       ('Mathematics', 'TUESDAY', 2),
       ('Singing', 'TUESDAY', 2),

       ('Astronomy', 'WEDNESDAY', 2),
       ('Geometry', 'WEDNESDAY', 2),

       ('Astronomy', 'FRIDAY', 2),
       ('Russian literature', 'FRIDAY', 2),


       ('Mathematics', 'MONDAY', 3),
       ('Astronomy', 'MONDAY', 3),

       ('Mathematics', 'TUESDAY', 3),
       ('Physics', 'TUESDAY', 3),

       ('Astronomy', 'WEDNESDAY', 3),
       ('Mathematics', 'WEDNESDAY', 3),

       ('Russian literature', 'FRIDAY', 3),
       ('Geometry', 'FRIDAY', 3);

