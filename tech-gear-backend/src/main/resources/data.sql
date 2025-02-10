-- Insert sample data into CUSTOMER
INSERT INTO CUSTOMER (USERNAME, PASSWORD, EMAIL, FULL_NAME, ADDRESS, IS_ADMIN, CREATED_AT, UPDATED_AT) VALUES
('admin', '1', 'admin@gmail.com', 'Tan Thanh', 'Quan 7', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user1', '1', 'user1@gmail.com', 'Tan Thanh', 'Quan 7', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user2', '1', 'user2@email.com', 'Hoang Phuc', 'Cu Chi', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user3', '1', 'user3@email.com', 'Hoang Tam', 'Cu Chi', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user4', '1', 'user4@email.com', 'Hong Hoa', 'Cu Chi', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample data into CATEGORY
INSERT INTO CATEGORY (NAME, DESCRIPTION, CREATED_AT, UPDATED_AT) VALUES
('Motherboard', 'Bo mạch chủ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CPU', 'Chip xử lý trung tâm', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('GPU', 'Card màn hình', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('RAM', 'Bộ nhớ động', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fan', 'Quạt tản nhiệt case', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample data into PRODUCT
INSERT INTO PRODUCT (CATEGORY_ID, NAME, DESCRIPTION, PRICE, DISCOUNT_PERCENTAGE, STOCK, AVAILABLE, IMAGE_URL, CREATED_AT, UPDATED_AT) VALUES
(1, 'Motherboard 1', 'Bo mạch chủ 1', 760.59, 10, 20, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Motherboard 2', 'Bo mạch chủ 2', 345.69, 10, 20, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Motherboard 3', 'Bo mạch chủ 3', 888.56, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'CPU 1', 'Chip xử lý trung tâm 1', 399.76, 10, 30, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'CPU 2', 'Chip xử lý trung tâm 2', 655.75, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'CPU 3', 'Chip xử lý trung tâm 3', 968.56, 10, 25, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'GPU 1', 'Card màn hình 1', 1200.78, 10, 20, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'GPU 2', 'Card màn hình 2', 970.99, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'GPU 3', 'Card màn hình 3', 1399.58, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'RAM 1', 'Bộ nhớ động 1', 120.99, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'RAM 2', 'Bộ nhớ động 2', 139.99, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'RAM 3', 'Bộ nhớ động 3', 200.58, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Fan 1', 'Quạt tản nhiệt case 3', 67.98, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Fan 2', 'Quạt tản nhiệt case 3', 75.15, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Fan 3', 'Quạt tản nhiệt case 3', 35.99, 10, 10, TRUE, 'http://example.com/image.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample data into CART
INSERT INTO CART (CUSTOMER_ID, TOTAL_PRICE, CREATED_AT, UPDATED_AT) VALUES
(1, 0.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 1000.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 0.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 1500.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 0.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample data into CART_DETAIL
INSERT INTO CART_DETAIL (CART_ID, PRODUCT_ID, STOCK) VALUES
(2, 1, 1),
(2, 4, 1),
(2, 7, 1),
(2, 10, 1),
(4, 2, 1),
(4, 5, 1),
(4, 8, 1),
(4, 11, 1);

-- Insert sample data into INVOICE
INSERT INTO INVOICE (CUSTOMER_ID, TOTAL_PRICE, STATUS, CREATED_AT, UPDATED_AT) VALUES
(3, 1499.99, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 2499.49, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 2399.99, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 1599.49, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample data into INVOICE_DETAIL
INSERT INTO INVOICE_DETAIL (INVOICE_ID, PRODUCT_ID, STOCK, PRICE, DISCOUNT_PERCENTAGE) VALUES
(1, 1, 2, 500.25, 10),
(1, 4, 1, 499.49, 0),
(2, 2, 2, 500.25, 10),
(2, 5, 1, 499.49, 0),
(2, 10, 2, 500.25, 10),
(3, 4, 1, 499.49, 0),
(3, 7, 1, 499.49, 10),
(3, 12, 2, 500.25, 0),
(4, 2, 1, 499.49, 0),
(4, 6, 1, 499.49, 10),
(4, 11, 2, 500.25, 10);