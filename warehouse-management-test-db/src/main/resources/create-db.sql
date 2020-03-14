DROP TABLE IF EXISTS records;
DROP TABLE IF EXISTS product;

CREATE TABLE product (
 product_id INT NOT NULL AUTO_INCREMENT,
 product_name VARCHAR(255) NOT NULL UNIQUE,
 PRIMARY KEY (product_id)
);

CREATE TABLE records (
 product_record_id INT NOT NULL AUTO_INCREMENT,
 product_id INT NOT NULL,
 number_of_product INT NOT NULL,
 product_record_date DATE NOT NULL,
 deal_type TINYINT NOT NULL,
 PRIMARY KEY(product_record_id),
 FOREIGN KEY(product_id) REFERENCES product(product_id)
);