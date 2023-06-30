create table if not exists hotel
(
    id           varchar(36)  not null,
    name         varchar(50)  not null,
    description  varchar(100) not null,
    hotel_cep    varchar(9)   not null,
    hotel_street varchar(50)  not null,
    locality_id  varchar(36)  not null,
    category_id  varchar(36)  not null,
    constraint hotel_pk
        primary key (id),
    constraint hotel_category_id_fk
        foreign key (category_id) references hotel_category (id),
    constraint hotel_locality_id_fk
        foreign key (locality_id) references locality (id)
);
