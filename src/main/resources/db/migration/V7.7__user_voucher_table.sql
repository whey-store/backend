create table if not exists user_voucher
(
    id                          int auto_increment,
    user_id                     int not null,
    voucher_id                  int not null,
    deleted                     tinyint(1) default 0 null,
    created                     timestamp null,
    modified                    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    # PK
    PRIMARY KEY (`id`),
    # FK
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)charset=utf8;