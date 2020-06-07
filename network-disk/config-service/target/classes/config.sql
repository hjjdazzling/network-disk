create database network_disk_config;

--配置表
create table if not exists config_info (
	id serial primary key,
	key varchar(255) not null,
	value varchar(255) not null,
	application varchar(255) not null,
	label varchar(255) not null,
	profile varchar(255) not null,
	description text,
	unique (key, application, label, profile)
);

comment on table config_info is '配置表';
comment on column config_info.id is '逻辑自增主键';
comment on column config_info.application is '微服务名';
comment on column config_info.label is '分支';
comment on column config_info.profile is '环境:development production test';
comment on column config_info.description is '描述';