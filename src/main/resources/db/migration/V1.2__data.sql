INSERT INTO users (id,username, password, name, lastName, patronymic)
VALUES
(1,'petia@mail.ru', '$2a$10$HSjtIauaVVTqCiy2DqOayuRs28yNXLku9PQDhAclSHS6K1amMly/i', 'Пётр', 'Иванов', 'Романович'),
(2,'vova@mail.ru', '$2a$10$HSjtIauaVVTqCiy2DqOayuRs28yNXLku9PQDhAclSHS6K1amMly/i', 'Владимир', 'Погорелов', 'Иванович');

INSERT INTO users (id,username, password, name, lastName)
VALUES
(3,'vasia@mail.ru', '$2a$10$HSjtIauaVVTqCiy2DqOayuRs28yNXLku9PQDhAclSHS6K1amMly/i', 'Василий', 'Погорелов');

INSERT INTO roles (id,name)
VALUES
(1,'ROLE_USER');

INSERT INTO user_roles (user_id, role_id)
VALUES
(1, 1),
(2, 1),
(3, 1);

INSERT INTO accounts (id, user_id, status, balance)
VALUES
('40817810570000123456', 1, TRUE, 1250000.00),
('40817810570000555353', 2, TRUE, 516.21),
('40817810570000148822', 3, FALSE, 0.00),
('40817810570000228999', 3, TRUE, 2618.13);

INSERT INTO operations (id, account_id, operation, money, time_operation)
VALUES
(1,'40817810570000148822', 3, NULL, '1999-08-25 12:01:6.55'),
(2,'40817810570000123456', 0, 250000.00, '1999-09-16 15:41:30.14');


