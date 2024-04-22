create table if not exists voucher
(
    id                          int auto_increment,
    store_id                    int not null,
    name                        varchar(50) null,
    percent_reduction           float default null,
    quantity                    int null,
    start_day                   timestamp null,
    end_day                     timestamp null,
    deleted                     tinyint(1) default 0 null,
    created                     timestamp null,
    modified                    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    # PK
    PRIMARY KEY (`id`),
    # FK
    FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
    )charset=utf8;