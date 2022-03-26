-- create table project
create table project
(
    id         bigserial primary key,
    name       varchar                  not null,
    start_date timestamp with time zone not null,
    end_date   timestamp with time zone not null,
    status     varchar                  not null,
    priority   int                      not null
);
alter table project
    add constraint project_name_id_unique unique (name, id);

-- create table task
create table task
(
    id          bigserial primary key,
    name        varchar not null,
    status      varchar not null,
    description varchar not null,
    project_id bigint references project(id),
    priority    int     not null
);

alter table task
    add constraint task_name_id_unique unique (name, id);