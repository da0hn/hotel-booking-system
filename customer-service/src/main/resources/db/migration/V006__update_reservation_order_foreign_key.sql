alter table reservation_order
    add constraint reservation_order_fk foreign key (customer_id) references customer (id);
