create table if not exists maintenances
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    user_id       integer  not null,
    maintenance_type       varchar(50)  not null,
    vehicle_id       integer  not null,
    details    varchar(255)  not null
);