create table user
(
    user_id     bigint primary key comment '用户id',
    username    varchar(50)   default '未知' comment '用户名称',
    email       varchar(100) not null comment '邮箱',
    password    varchar(512) not null comment '用户密码',
    avatar      text         not null comment '头像：如果用户传入头像，那么就用户的，如果没传，那么就用一个默认的',
    description varchar(512)  default '该用户很懒，尚未填写用户介绍' comment '用户介绍',
    tags        varchar(1024) default '[]' comment '标签',
    roles       tinyint       default 0 comment '普通用户是0，管理员是1',
    create_time datetime      default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime      default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    is_delete   tinyint       default 0 comment '逻辑删除字段：0未删除，1已删除'
) engine = innodb
  default charset = utf8mb4 comment '用户表';
create table user_process
(
    process_id     bigint primary key comment '进度id',
    today_content  varchar(1024) not null comment '今日学习内容',
    target_content varchar(1024) default '暂无目标' comment '目标内容',
    user_id        bigint        not null comment '用户id',
    expected_time  datetime comment '预计完成时间',
    create_time    datetime      default CURRENT_TIMESTAMP comment '创建时间',
    update_time    datetime      default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    is_delete      tinyint       default 0 comment '逻辑删除字段：0未删除，1已删除'
) engine = innodb
  default charset = utf8mb4 comment '学习进度表';
create table tags
(
    tag_id      bigint primary key comment '用户id',
    tag_name    varchar(100) default '[]' comment '标签内容',
    create_time datetime     default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    is_delete   tinyint      default 0 comment '逻辑删除字段：0未删除，1已删除'
) engine = innodb
  default charset = utf8mb4 comment '标签表';
create table process_tag
(
    record_id   bigint primary key comment '进度和标签的中间表id',
    user_id     bigint not null comment '用户id',
    tag_id      bigint not null comment '标签id',
    tag_name    varchar(100) default '[]' comment '标签内容',
    create_time datetime     default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    is_delete   tinyint      default 0 comment '逻辑删除字段：0未删除，1已删除'
) engine = innodb
  default charset = utf8mb4 comment '进度和标签的中间表id';
