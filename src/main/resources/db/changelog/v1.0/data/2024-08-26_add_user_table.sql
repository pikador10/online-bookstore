CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name        VARCHAR(64)    NOT NULL,
    last_name         VARCHAR(64)    NOT NULL,
    email             VARCHAR(64)    NOT NULL UNIQUE,
    password          VARCHAR(64)    NOT NULL,
    shipping_address  VARCHAR(255)   DEFAULT NULL,
    is_deleted        BOOLEAN        DEFAULT FALSE
);
