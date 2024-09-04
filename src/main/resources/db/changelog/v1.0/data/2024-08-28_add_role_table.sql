START TRANSACTION;

CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role              VARCHAR(64)    NOT NULL,
    is_deleted        BOOLEAN        DEFAULT FALSE
);

INSERT INTO role (role) VALUES ('ADMIN'), ('USER');

CREATE TABLE IF NOT EXISTS user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

INSERT INTO user (first_name, last_name, email, password)
    VALUES ('Vin', 'Diesel', 'admin@admin.com', '$2a$10$G1JOlQ3WhnlweDCvpoAsL.iwMQUbls38tffktey8OJ64GyzjtFUu2');
SELECT id INTO @adminRoleId FROM role WHERE role = 'ADMIN';
SELECT id INTO @adminId FROM user WHERE email = 'admin@admin.com';
INSERT INTO user_role (user_id, role_id) VALUES (@adminId, @adminRoleId);

COMMIT;
