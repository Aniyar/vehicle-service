create table if not exists auctions
(
    id             bigserial    not null primary key,
    created_at     timestamp    not null default now(),
    updated_at     timestamp,
    deleted_at     timestamp,
    is_deleted     smallint     not null default 0,

    vehicle_id       integer  not null,
    start_date      date    not null,
    end_date      date    not null,
    current_bid      decimal not null,
    status          varchar(50) not null,
    bid_user_id     integer
);