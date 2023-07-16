create table if not exists booking_room
(
    id         varchar(36) not null,
    room_id    varchar(36) not null,
    booking_id varchar(36) not null,
    price      decimal     not null,
    quantity   integer     not null,
    constraint booking_room_pk primary key (id),
    constraint booking_room_booking_id_fk foreign key (booking_id) references booking (id),
    constraint booking_room_room_id_fk foreign key (room_id) references room (id)
);
