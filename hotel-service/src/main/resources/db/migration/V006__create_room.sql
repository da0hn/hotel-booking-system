create table if not exists room
(
    id            varchar(36)  not null,
    name          varchar(30)  not null,
    description   varchar(100) not null,
    capacity      int          not null,
    current_price decimal      not null,
    hotel_id      varchar(36)  not null,
    constraint room_pk
        primary key (id),
    constraint room_hotel_id_fk
        foreign key (hotel_id) references hotel (id)
);

