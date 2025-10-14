use `order`;
    CREATE TABLE orders_tbl(
    orderId BIGINT AUTO_INCREMENT PRIMARY KEY,
    productId BIGINT NOT NULL ,
    quantity BIGINT NOT NULL ,
    orderStatus VARCHAR(100),
    orderDate TIMESTAMP NULL,
    amount BIGINT,
    paymentMode VARCHAR(100)
)