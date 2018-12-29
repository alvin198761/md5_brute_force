drop table if exists md5_len20;

/*==============================================================*/
/* Table: md5_len20                                             */
/*==============================================================*/
create table md5_len20
(
   id                   bigint not null auto_increment comment '主键',
   data_key                  varchar(20) comment 'j键',
   md5_16               varchar(50) comment 'md5-16',
   md5_32               varchar(50) comment 'md5-32',
   primary key (id)
);

alter table md5_len20 comment '长度20以内的md5全集';
