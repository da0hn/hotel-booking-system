CREATE TABLE IF NOT EXISTS reservation_order
(
    id             varchar(36) not null,
    customer_id    varchar(36) not null,
    hotel_id       varchar(36) not null,
    guests         int         not null,
    check_in       date        not null,
    check_out      date        not null,
    total_price    decimal     not null,
    current_status varchar(50) not null,
    constraint reservation_order_pk primary key (id)
);
