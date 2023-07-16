create table if not exists booking
(
    id          varchar(36) not null,
    customer_id varchar(36) not null,
    check_in    date        not null,
    check_out   date        not null,
    status      varchar(50) not null,
    total_price decimal     not null,
    constraint booking_pk primary key (id)
);
