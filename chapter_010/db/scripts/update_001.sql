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
  occupied_account_id INTEGER REFERENCES accounts (phone_id)
);

INSERT INTO halls (seat) VALUES (1);
INSERT INTO halls (seat) VALUES (2);
INSERT INTO halls (seat) VALUES (3);
INSERT INTO halls (seat) VALUES (4);
INSERT INTO halls (seat) VALUES (5);
INSERT INTO halls (seat) VALUES (6);
INSERT INTO halls (seat) VALUES (7);
INSERT INTO halls (seat) VALUES (8);
INSERT INTO halls (seat) VALUES (9);

