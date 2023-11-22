create table if not exists vehicle_users
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    username       varchar(50)  not null,
    first_name     varchar(50)  not null,
    last_name      varchar(50)  not null,
    email          varchar(50)  not null,
    phone_number   varchar(50)  not null,
    role           varchar(50)  not null,
    password       varchar(50)  not null,
    file_link_id   integer
);

create unique index if not exists uk_vehicle_users_username
    on vehicle_users (username) where is_deleted = 0;

create unique index if not exists uk_vehicle_users_email
    on vehicle_users (email) where is_deleted = 0;

create unique index if not exists uk_vehicle_users_phone_number
    on vehicle_users (phone_number) where is_deleted = 0;