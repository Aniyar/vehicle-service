create table if not exists vehicles
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    model       varchar(50)  not null,
    make_year     integer  not null,
    licence_plate      varchar(50)  not null,
    capacity          integer  not null,
    vin   varchar(50)  not null,
    fuel_type           varchar(50)  not null
);
