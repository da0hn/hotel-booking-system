create table if not exists reservation_order_history
(
    id                   varchar(36) not null,
    reservation_order_id varchar(36) not null,
    status               varchar(50) not null,
    occurred_at          datetime    not null,
    constraint primary key (id),
    constraint reservation_order_history_reservation_order_fk foreign key (reservation_order_id) references reservation_order (id)
);
