create table if not exists user
(
    id              int auto_increment primary key,
    name            varchar(50) null,
    email           varchar(150) null,
    phone           varchar(11) null,
    avatar    varchar(255) null,
    gender    int null,
    age       int null,
    created   timestamp null,
    modified  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)charset=utf8;