alter table booking
    add created_at datetime default now() null;

alter table booking
    add updated_at datetime default now() null;

