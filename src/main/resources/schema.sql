DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS diary;
 
CREATE TABLE accounts (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE diary (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  diary_date CHAR(8) NOT NULL,
  contents VARCHAR(500) NOT NULL
);
 
INSERT INTO accounts (email) VALUES
  ('email1@email.com'),
  ('email2@email.com'),
  ('email3@email.com');
  
  INSERT INTO diary (diary_date, contents) VALUES 
  ('20190910', 'contents1'),
  ('20190911', 'contents2'),
  ('20190911', 'contents3'),
  ('20190913', 'contents4'),
  ('20190913', 'contents5');