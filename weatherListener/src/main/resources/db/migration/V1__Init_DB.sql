create sequence hibernate_sequence start 1 increment 1;

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table usr
(
    id       int8 not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table weather_records
(
    id          int8 not null,
    date        varchar(255),
    pressure    varchar(255),
    temperature varchar(255),
    cloudiness  varchar(255),
    wind_speed  varchar(255),
    user_id     int8,
    primary key (id)
);
alter table if exists user_role
    add constraint user_role_user_fk foreign key (user_id) references usr;
alter table if exists weather_records
    add constraint weather_records_user_fk foreign key (user_id) references usr;
