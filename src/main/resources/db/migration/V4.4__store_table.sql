create table if not exists store
(
    id              int auto_increment primary key,
    user_id         int not null,
    name            varchar(50) null,
    avatar          varchar(255) null,
    address         varchar(255) null,
    description     varchar(255) null,
    open_time       timestamp null,
    close_time      timestamp null,
    status          varchar(50) null,
    created         timestamp null,
    modified        timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)charset=utf8;