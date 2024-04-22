create table if not exists product
(
    id              int auto_increment,
    store_id        int not null,
    name            varchar(50) null,
    price           float default null,
    image           varchar(255) null,
    description     varchar(255) null,
    amount          int,
    deleted         tinyint(1) default 0 null,
    created         timestamp null,
    modified        timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    # PK
    PRIMARY KEY (`id`),
    # FK
    FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)charset=utf8;