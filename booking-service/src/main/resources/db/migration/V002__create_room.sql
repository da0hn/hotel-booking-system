create table if not exists room
(
    id            varchar(36) not null,
    hotel_id      varchar(36) not null,
    current_price decimal     not null,
    capacity      int         not null,
    quantity      int         not null,
    constraint room_pk primary key (id)
);
