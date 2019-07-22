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

INSERT INTO halls (seat, price) VALUES (1, 700);
INSERT INTO halls (seat, price) VALUES (2, 700);
INSERT INTO halls (seat, price) VALUES (3, 700);
INSERT INTO halls (seat, price) VALUES (4, 600);
INSERT INTO halls (seat, price) VALUES (5, 600);
INSERT INTO halls (seat, price) VALUES (6, 600);
INSERT INTO halls (seat, price) VALUES (7, 500);
INSERT INTO halls (seat, price) VALUES (8, 500);
INSERT INTO halls (seat, price) VALUES (9, 500);
