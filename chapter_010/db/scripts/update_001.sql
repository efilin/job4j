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
