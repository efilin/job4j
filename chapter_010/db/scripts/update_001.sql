CREATE TABLE IF NOT EXISTS users (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(100),
  login       VARCHAR(100),
  email       VARCHAR(100),
  create_date VARCHAR(100),
  password    VARCHAR(100),
  role        VARCHAR(100),
  country     VARCHAR(100),
  city        VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS countries (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS cities (
  id         SERIAL PRIMARY KEY,
  country_id INTEGER REFERENCES countries (id),
  name       VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS accounts (
  phone_id INTEGER PRIMARY KEY,
  name     VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS halls (
  id                  SERIAL PRIMARY KEY,
  seat                INTEGER,
  price               INTEGER,
  occupied_account_id INTEGER REFERENCES accounts (phone_id)
);

INSERT INTO halls (seat, price) VALUES (11, 700);
INSERT INTO halls (seat, price) VALUES (12, 700);
INSERT INTO halls (seat, price) VALUES (13, 700);
INSERT INTO halls (seat, price) VALUES (21, 600);
INSERT INTO halls (seat, price) VALUES (22, 600);
INSERT INTO halls (seat, price) VALUES (23, 600);
INSERT INTO halls (seat, price) VALUES (31, 500);
INSERT INTO halls (seat, price) VALUES (32, 500);
INSERT INTO halls (seat, price) VALUES (33, 500);
