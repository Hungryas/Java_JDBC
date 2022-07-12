CREATE TABLE IF NOT EXISTS users (
    id          varchar         PRIMARY KEY,
    phone       varchar(20)     NOT NULL,
    email       varchar(50)     NOT NULL,
    first_name  varchar(100)    NOT NULL,
    last_name   varchar(100)    NOT NULL,
    middle_name varchar(100)
    );

CREATE TABLE IF NOT EXISTS rooms (
    id          varchar         PRIMARY KEY,
    room_number varchar(100)    NOT NULL,
    floor       int4            NOT NULL,
    room_type   varchar(50)     NOT NULL,
    description varchar         ,
    price       int4            NOT NULL
    );

CREATE TABLE IF NOT EXISTS bookings (
    id          varchar     PRIMARY KEY,
    check_in    timestamp   NOT NULL,
    check_out   timestamp   NOT NULL,
    user_id     varchar     REFERENCES users ON DELETE CASCADE,
    room_id     varchar     REFERENCES rooms ON DELETE CASCADE
    );