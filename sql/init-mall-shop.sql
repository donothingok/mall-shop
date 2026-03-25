-- 初始化数据库（MySQL 8+）
CREATE DATABASE IF NOT EXISTS mall_shop DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mall_shop;

CREATE TABLE IF NOT EXISTS sys_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR(32) NOT NULL UNIQUE,
  username VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(128) NOT NULL,
  role_codes VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL,
  status TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS cart_item (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR(32) NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  created_at DATETIME NOT NULL,
  INDEX idx_cart_user_id(user_id)
);

CREATE TABLE IF NOT EXISTS orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_no VARCHAR(64) NOT NULL UNIQUE,
  user_id VARCHAR(32) NOT NULL,
  status VARCHAR(32) NOT NULL,
  address VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  INDEX idx_orders_user_id(user_id)
);

CREATE TABLE IF NOT EXISTS order_item (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  product_name VARCHAR(128) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  quantity INT NOT NULL,
  INDEX idx_order_item_order_id(order_id)
);

INSERT INTO sys_user(user_id, username, password, role_codes)
VALUES
  ('10001', 'user', '123456', 'user'),
  ('90001', 'admin', '123456', 'admin,user')
ON DUPLICATE KEY UPDATE username = VALUES(username);

INSERT INTO product(id, name, price, stock, status)
VALUES
  (1, '示例商品A', 99.00, 120, 1),
  (2, '示例商品B', 199.00, 80, 1)
ON DUPLICATE KEY UPDATE name = VALUES(name), price = VALUES(price), stock = VALUES(stock), status = VALUES(status);
