DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id INT NOT NULL AUTO_INCRIMENT,
    product_title VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (product_id)
);

DROP TABLE IF EXISTS product_record;
CREATE TABLE product_record (
    product_record_id INT NOT NULL AUTO_INCRIMENT,
    product_id INT NOT NULL,
    number_of_product INT NOT NULL,
    product_record_date DATE NOT NULL,
    deal_type SMALLINT NOT NULL,
    PRIMARY KEY(product_record_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id)
);