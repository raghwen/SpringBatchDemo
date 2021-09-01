DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  department VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

INSERT INTO USER (name, department, career) VALUES
  ('Aliko', 'Tech', 'Billionaire Industrialist'),
  ('Bill', 'Tech', 'Billionaire Tech Entrepreneur'),
  ('Ram', 'Sales', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Sales', 'Billionaire Oil Magnate');