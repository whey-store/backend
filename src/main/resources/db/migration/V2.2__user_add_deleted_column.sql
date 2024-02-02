ALTER TABLE user
ADD deleted tinyint(1) default 0 null comment '0: Undeleted user 1: Deleted user'