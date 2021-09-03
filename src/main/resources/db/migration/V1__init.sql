CREATE TABLE users(
    id serial,
    username    varchar(50) NOT NULL,
    password    varchar(80) NOT NULL,
    name        varchar(50) NOT NULL,
    lastName    varchar(50) NOT NULL,
    patronymic  varchar(50),
    PRIMARY KEY (id),
    CONSTRAINT UK_USERNAME UNIQUE (username)
);

CREATE TABLE roles(
    id          serial,
    name        varchar(50) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UK_NAME UNIQUE (name)
);

CREATE TABLE user_roles (
    user_id     int NOT NULL,
    role_id     int NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE accounts(
    id          varchar(50) NOT NULL,
    user_id     int NOT NULL,
    status      boolean NOT NULL,
    balance     decimal(12,2) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
    REFERENCES users (id)
);

CREATE TABLE operations(
    id              serial,
    account_id      varchar(50) NOT NULL,
    operation       int NOT NULL,
    money           money,
    time_operation  timestamp NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT FK_USER_ID FOREIGN KEY (account_id)
    REFERENCES accounts (id)
);