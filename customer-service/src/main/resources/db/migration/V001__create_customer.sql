CREATE TABLE IF NOT EXISTS customer
(
    id   VARCHAR(36) NOT NULL,
    name VARCHAR(36) NOT NULL,
    cpf  VARCHAR(11) NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (id)
);
