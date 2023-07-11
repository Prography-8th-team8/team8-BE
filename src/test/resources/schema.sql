create table store
(
    id          bigint           not null
        primary key,
    created_at  timestamp(6)     not null,
    deleted_at  timestamp(6),
    modified_at timestamp(6)     not null,
    city        varchar(255)     not null,
    district    varchar(255)     not null,
    latitude    double precision not null,
    location    varchar(255)     not null,
    longitude   double precision not null,
    name        varchar(255)     not null,
    share_link  varchar(255),
    thumbnail   varchar(255)
);

create table store_tag
(
    id          bigint       not null
        primary key,
    created_at  timestamp(6) not null,
    deleted_at  timestamp(6),
    modified_at timestamp(6) not null,
    store_type  varchar(255) not null
);

create table store_and_tag
(
    id           bigint       not null
        primary key,
    created_at   timestamp(6) not null,
    deleted_at   timestamp(6),
    modified_at  timestamp(6) not null,
    store_id     bigint
        constraint fkhejw9xny80610tsyy28tamgc4
            references store,
    store_tag_id bigint
        constraint fkqg9nlurg20ti00lwarvbveukq
            references store_tag
);

create table store_image_urls
(
    store_id   bigint not null
        constraint fk71m9toawwa9o8akg23sw59get
            references store,
    image_urls varchar(255)
);
