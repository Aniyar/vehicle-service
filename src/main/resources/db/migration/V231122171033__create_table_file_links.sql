create table if not exists file_links
(
    id              integer    not null primary key,
    created_at      timestamp    not null default now(),
    updated_at      timestamp,
    deleted_at      timestamp,
    is_deleted      smallint     not null default 0,

    owner_user_uuid integer  not null,
    file_name       varchar(255) not null,
    storage_key     varchar(36)  not null,
    bucket_name     varchar(255) not null,
    content_type    varchar(60),
    is_viewable     boolean      default false
);
create unique index if not exists uk_file_links_url on file_links (storage_key) where is_deleted = 0;