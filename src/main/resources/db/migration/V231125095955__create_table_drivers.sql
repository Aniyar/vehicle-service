create table if not exists drivers
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    user_id       integer  not null,
    address       varchar(50)  not null,
    license_number varchar(50)  not null,
    rating          decimal  not null,
    vehicle_id    integer
);