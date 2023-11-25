create table if not exists fuels
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    user_id       integer  not null,
    fuel_type       varchar(50)  not null,
    vehicle_id       integer  not null,
    price_per_liter decimal  not null,
    number_liters          integer  not null,
    price    decimal  not null
);