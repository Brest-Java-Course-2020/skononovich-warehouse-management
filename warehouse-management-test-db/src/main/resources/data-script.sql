INSERT INTO product(product_id, product_name) VALUES (1, 'Product 1');
INSERT INTO product(product_id, product_name) VALUES (2, 'Product 2');
INSERT INTO product(product_id, product_name) VALUES (3, 'Product 3');

INSERT INTO records(record_id, product_id, quantity, deal_date, deal_type)
VALUES (1, 1, 3, '2020-01-12', 1);
INSERT INTO records(record_id, product_id, quantity, deal_date, deal_type)
VALUES (2, 1, 1, '2020-01-13', 0);
INSERT INTO records(record_id, product_id, quantity, deal_date, deal_type)
VALUES (3, 2, 56, '2020-01-14', 1);
INSERT INTO records(record_id, product_id, quantity, deal_date, deal_type)
VALUES (4, 1, 5, '2020-07-12', 1);
INSERT INTO records(record_id, product_id, quantity, deal_date, deal_type)
VALUES (5, 2, 3, '2020-08-12', 0);
