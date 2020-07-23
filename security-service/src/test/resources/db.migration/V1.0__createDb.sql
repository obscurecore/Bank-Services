alter table if exists user_role
    drop constraint if exists FKj345gk1bovqvfame88rcx7yyx;
alter table if exists users
    drop constraint if exists FK1w17j63m1bjcqucudpnfhxi2t;
drop table if exists user_role cascade;
drop table if exists users cascade;
drop table if exists verification_token cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table users
(
    id              int8 not null,
    email           varchar(255),
    password        varchar(255),
    state           varchar(255),
    username        varchar(255),
    verification_id int8,
    primary key (id)
);
create table verification_token
(
    id          int8 not null,
    expiry_date timestamp,
    token       varchar(255),
    primary key (id)
);
alter table if exists user_role
    add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users;
alter table if exists users
    add constraint FK1w17j63m1bjcqucudpnfhxi2t foreign key (verification_id) references verification_token
