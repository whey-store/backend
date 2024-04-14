create table if not exists role
(
    id        int auto_increment  primary key,
    name      varchar(50),
    type      varchar(100),
    created   timestamp null,
    modified  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)charset=utf8;


#
#insert value into role TABLE
#
INSERT INTO role (name, type) VALUES
('Admin', 'Admin'),
('Store', 'Store'),
('Shipping Unit', 'Shipping_Unit'),
('Shipper', 'Shipper'),
('Customer', 'Customer');

