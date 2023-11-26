create table if not exists routes
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    driver_id       integer,
    staff_id       integer not null,
    vehicle_id       integer,
    start_point       varchar(50)  not null,
    end_point       varchar(50)  not null,

    start_time timestamp,
    end_time timestamp,
    status    varchar(50)  not null
);