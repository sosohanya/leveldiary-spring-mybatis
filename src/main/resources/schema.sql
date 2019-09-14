DROP TABLE IF EXISTS accounts;
 
CREATE TABLE accounts (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(255) NOT NULL
);
 
INSERT INTO accounts (email) VALUES
  ('email1@email.com'),
  ('email2@email.com'),
  ('email3@email.com');