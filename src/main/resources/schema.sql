DROP TABLE IF EXISTS testtbl;
 
CREATE TABLE testtbl (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);
 
INSERT INTO testtbl (name) VALUES
  ('name1'),
  ('name2'),
  ('name3');