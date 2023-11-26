create table if not exists vehicle_locations
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    vehicle_id       integer not null,
    longitude       varchar(50)  not null,
    latitude       varchar(50)  not null
);