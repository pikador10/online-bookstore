CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255)    NOT NULL,
    author       VARCHAR(255)    NOT NULL,
    isbn         VARCHAR(255)    NOT NULL UNIQUE,
    price        DECIMAL(6, 2)   NOT NULL,
    description  TEXT            DEFAULT NULL,
    cover_image  VARCHAR(255)    DEFAULT NULL,
    is_deleted   BOOLEAN         DEFAULT FALSE
);
