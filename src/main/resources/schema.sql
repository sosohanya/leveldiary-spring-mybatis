DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS diary;
 
CREATE TABLE account (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE diary (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  diary_date CHAR(8) NOT NULL,
  contents VARCHAR(500) NOT NULL,
  account_id BIGINT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);
 
INSERT INTO account (email, password) VALUES
  ('email1@email.com', 'pwd'),
  ('email2@email.com', 'pwd'),
  ('email3@email.com', 'pwd');
  
INSERT INTO diary (diary_date, contents, account_id) VALUES 
  ('20190910', 'contents1_by1', 1),
  ('20190911', 'contents2_by1', 1),
  ('20190911', 'contents3_by1', 1),
  ('20190913', 'contents4_by2', 2),
  ('20190913', 'contents5_by2', 2);