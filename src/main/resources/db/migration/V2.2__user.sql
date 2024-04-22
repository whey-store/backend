create table if not exists user
(
    id        int auto_increment,
    username      varchar(50) null,
    role_id   int not null,
    password  varchar(255) not null,
    email     varchar(150) null,
    phone     varchar(11) null,
    avatar    varchar(255) null,
    gender    int null,
    age       int null,
    created   timestamp null,
    modified  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    # PK
    PRIMARY KEY (`id`),
    # FK
    FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)charset=utf8;