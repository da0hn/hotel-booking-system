INSERT INTO booking (id, customer_id, check_in, check_out, status, total_price)
VALUES ('1faf397b-5eb8-4be4-92d7-22cbd476dad3',
        '4853e28a-90bc-4700-9909-9b0e799e72b4',
        '2023-06-15',
        '2023-06-25',
        'CONFIRMED',
        456.0);

INSERT INTO booking_room (id, room_id, booking_id, price, quantity)
VALUES ('67ae1944-6e40-4c6a-9c9c-5c415b811c90',
        '2223dc04-831a-4bac-aef5-e22195575cc6',
        '1faf397b-5eb8-4be4-92d7-22cbd476dad3',
        456.0,
        1);

INSERT INTO booking (id, customer_id, check_in, check_out, status, total_price)
VALUES ('4df75ab0-91f1-4b95-bedb-61c0863e2d4b',
        '1844738a-e16c-4807-b99f-b6fbef2272f6',
        '2023-07-01',
        '2023-07-12',
        'CONFIRMED',
        916.0);

INSERT INTO booking_room (id, room_id, booking_id, price, quantity)
VALUES ('f27a7032-7d7d-4c84-adeb-f3dfb6c6fe8c',
        '2c5c52bb-739d-468b-a2a2-b8b917a2004f',
        '4df75ab0-91f1-4b95-bedb-61c0863e2d4b',
        458.0,
        1);

INSERT INTO booking_room (id, room_id, booking_id, price, quantity)
VALUES ('3344d44b-608e-4157-9a4e-a40b0238ff2a',
        'b61bc003-d8ff-4d78-b983-7da08af83510',
        '4df75ab0-91f1-4b95-bedb-61c0863e2d4b',
        458.0,
        1);

INSERT INTO booking (id, customer_id, check_in, check_out, status, total_price)
VALUES ('c922da13-0dc9-4f8d-9b7e-2de53e594f8a',
        'fd0822fd-dc24-41f6-ab12-5b5f69e59318',
        '2023-07-13',
        '2023-07-20',
        'CONFIRMED',
        916.0);

INSERT INTO booking_room (id, room_id, booking_id, price, quantity)
VALUES ('db993f79-859f-4635-b88b-99057b4aca75',
        '2c5c52bb-739d-468b-a2a2-b8b917a2004f',
        'c922da13-0dc9-4f8d-9b7e-2de53e594f8a',
        458.0,
        2);
