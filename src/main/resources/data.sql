DROP TABLE IF EXISTS products;

CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  prod_name VARCHAR(250) NOT NULL,
  category_id VARCHAR(250) NOT NULL,
  price VARCHAR(250) DEFAULT NULL
);

INSERT INTO product (prod_name, category_id, price) VALUES
  ('test', '2', '15'),
  ('test1', '3', '30'),
  ('test2', '4', '50');
  
CREATE TABLE user_details (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  user_type VARCHAR(250) NOT NULL,
  created_date DATE
);

INSERT INTO user_details (user_name, password, user_type, created_date) VALUES
  ('salvan', 'Qwerty@12', 'staff', '2021-06-16'),
  ('salvan123', 'Qwerty@12', 'affiliate', '2021-06-16'),
  ('salvan345', 'Qwerty@12', 'normal', '2021-06-16'),
  ('salvan789', 'Qwerty@12', 'normal', '2019-06-16');