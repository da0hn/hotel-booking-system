INSERT INTO hotel (id, name, description, hotel_cep, hotel_street, category_id, locality_id)
VALUES ('b69b768f-32d8-4c70-a3ef-b5ace438c5e7',
        'Amazon Plaza Hotel',
        'Localizado no centro de Cuiabá, perto de lojas e restaurantes, o Amazon Plaza apresenta decoração indiana. A propriedade oferece uma bela piscina no jardim com cascata e redes, além de Wi-Fi gratuito.',
        '78005-370',
        'Av Getulio Vargas, 600',
        'bcbc43a4-5a77-44e8-9cd4-7da67b66a390',
        '2e02993c-2b70-478e-82b2-63ff7a4991c1');
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('ebf84e50-6aa8-461b-837d-f3a541015595',
        'Quarto Duplo Deluxe',
        'Este quarto tem ar-condicionado, TV LCD de 32” com canais a cabo, cofre, frigobar e banheiro privativo com chuveiro de água quente. O Wi-Fi é grátis. Além disso, o serviço de quarto está disponível 24 horas por dia.',
        2,
        342,
        'b69b768f-32d8-4c70-a3ef-b5ace438c5e7',
        5);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('e86ff4c5-6f20-419e-8ce8-3140db9bd7a7',
        'Quarto com Cama King-size',
        'Este quarto tem ar-condicionado, TV LCD de 32” com canais a cabo, frigobar e banheiro privativo com chuveiro de água quente. O Wi-Fi grátis está disponível.',
        2,
        342,
        'b69b768f-32d8-4c70-a3ef-b5ace438c5e7',
        10);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('2223dc04-831a-4bac-aef5-e22195575cc6',
        'Quarto Triplo Deluxe',
        'Este quarto triplo dispõe de piso frio/em mármore, ar-condicionado e frigobar.',
        3,
        456,
        'b69b768f-32d8-4c70-a3ef-b5ace438c5e7',
        4);
-- --------------------------------------------------------------------------------------------------------------------
INSERT INTO hotel (id, name, description, hotel_cep, hotel_street, category_id, locality_id)
VALUES ('9f79796f-3c61-4cdd-bb4a-f662dc22f3a1', 'Getúlio Hotel',
        'Moderno e prático, o Getúlio Hotel está localizado no centro da cidade. A propriedade dispõe de acomodações com WiFi gratuito. Você pode relaxar com uma bebida na lanchonete do hotel.',
        '78005-370', 'Av. Getúlio Vargas, 262',
        'bcbc43a4-5a77-44e8-9cd4-7da67b66a390',
        '2e02993c-2b70-478e-82b2-63ff7a4991c1');
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('ec5f2c4f-7754-493d-96ab-81e1da5c2087',
        'Quarto Duplo',
        'Este quarto dispõe de piso frio, ar-condicionado, TV, guarda-roupa, frigobar e banheiro privativo com chuveiro de água quente. O Wi-Fi é grátis.',
        2,
        249,
        '9f79796f-3c61-4cdd-bb4a-f662dc22f3a1',
        6);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('3d2fa22e-75ff-410f-bb7d-822206f0ff02',
        'Quarto com 2 Camas de Solteiro',
        'Quarto com ar-condicionado, TV de tela plana a cabo e banheiro privativo.',
        2,
        250,
        '9f79796f-3c61-4cdd-bb4a-f662dc22f3a1',
        8);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('74537826-df7f-4bf7-a5af-27796c20b792',
        'Quarto Triplo',
        'Este quarto dispõe de piso frio, ar-condicionado, TV, guarda-roupa, frigobar e banheiro privativo com chuveiro de água quente. O Wi-Fi é grátis.',
        3,
        300,
        '9f79796f-3c61-4cdd-bb4a-f662dc22f3a1',
        4);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('22a85dc6-53e0-4921-82bc-90cd11fc7284',
        'Quarto Quádruplo',
        'Este quarto quádruplo dispõe de ar-condicionado.',
        4,
        360,
        '9f79796f-3c61-4cdd-bb4a-f662dc22f3a1',
        8);
-- --------------------------------------------------------------------------------------------------------------------
INSERT INTO hotel (id, name, description, hotel_cep, hotel_street, category_id, locality_id)
VALUES ('b166aa47-3af1-462f-bb12-8ac30ad0ed98',
        'Hotel Taiamã',
        'O Hotel Taiamã está localizado em Cuiabá e oferece uma piscina com cascata e acesso Wi-Fi gratuito. Um buffet de café da manhã variado, incluindo frutas da estação, sucos naturais, pratos frios e quentes, é servido diariamente. Especialidades regionais são servidas no almoço e no jantar, e o bar serve bebidas especiais.',
        '78008-000', 'Av. Historiador Rubens de Mendonça, 1184',
        'bcbc43a4-5a77-44e8-9cd4-7da67b66a390',
        '2e02993c-2b70-478e-82b2-63ff7a4991c1');
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('2c5c52bb-739d-468b-a2a2-b8b917a2004f',
        'Quarto Standard com Cama Queen-size',
        'Camas confortáveis',
        2,
        458,
        'b166aa47-3af1-462f-bb12-8ac30ad0ed98',
        5);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('b61bc003-d8ff-4d78-b983-7da08af83510',
        'Quarto Duplo',
        'Camas confortáveis',
        2,
        458,
        'b166aa47-3af1-462f-bb12-8ac30ad0ed98',
        2);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('e0a353a7-60ee-49d7-b0ca-b9217131ee3a',
        'Suíte Familía Deluxe',
        'Camas confortáveis',
        5,
        698,
        'b166aa47-3af1-462f-bb12-8ac30ad0ed98',
        3);
-- --------------------------------------------------------------------------------------------------------------------
INSERT INTO hotel (id, name, description, hotel_cep, hotel_street, category_id, locality_id)
VALUES ('04a84c00-d083-4b0e-b9f2-c039d26b1c43',
        'Pousada Villa Guiamares',
        'Situada no coração da vila da Chapada dos Guimarães, esta pousada está a apenas 100 m da Praça Central. A propriedade oferece uma piscina ao ar livre e um salão de jogos. O acesso à internet sem fio (Wi-Fi) e o estacionamento são gratuitos.',
        '78195-000',
        'Rua Neco Siqueira, 41',
        '424e8f34-8f3b-4702-8234-a0305085e822',
        'e1bf8402-9441-4774-8061-9444fc8bdb28');

INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('844c933f-b881-46f9-9533-e591ed214bc3',
        'Suíte Dupla Standard',
        'Camas confortáveis',
        2,
        499,
        '04a84c00-d083-4b0e-b9f2-c039d26b1c43',
        1);
-- --------------------------------------------------------------------------------------------------------------------
INSERT INTO hotel (id, name, description, hotel_cep, hotel_street, category_id, locality_id)
VALUES ('0bd67fc3-f057-4e9f-a04f-8219d5ae4408',
        'Pousada Canto dos Pássaros',
        'Situado na Chapada dos Guimarães, A Pousada Canto dos Pássaros oferece piscina ao ar livre, jardim, lounge compartilhado e Wi-Fi gratuito em todas as áreas. A propriedade dispõe de cozinha compartilhada e churrasqueira. A propriedade oferece estacionamento privativo gratuito e serviço de transfer (aeroporto) por um custo adicional.',
        '78195-000',
        'Rua das Violetas, 103',
        '424e8f34-8f3b-4702-8234-a0305085e822',
        'e1bf8402-9441-4774-8061-9444fc8bdb28');
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('3edafce7-e419-4f6b-b348-f61598992eab',
        'Suíte Deluxe',
        'Esta suite apresenta uma varanda, torradeira e utensílios de cozinha.',
        2,
        350,
        '0bd67fc3-f057-4e9f-a04f-8219d5ae4408',
        1);
INSERT INTO room (id, name, description, capacity, current_price, hotel_id, quantity)
VALUES ('25720690-8eef-4009-8e3f-d8c06cd6c85f',
        'Casa de 2 Quartos',
        'Esta casa de temporada dispõe de varanda, sofá e área de estar.',
        4,
        720,
        '0bd67fc3-f057-4e9f-a04f-8219d5ae4408',
        1);
