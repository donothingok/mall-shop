# Mall Shop 架构说明

## 模块划分

- 用户与认证：`modules/auth`
- 商品：`modules/product`
- 购物车：`modules/cart`
- 订单：`modules/order`
- 管理端：`modules/admin`
- 公共能力：`common`

## 已实现 API（演示版）

- 公共：`/api/v1/public/auth/login`, `/api/v1/public/products`
- 用户侧（需登录）：`/api/v1/cart`, `/api/v1/orders`
- 管理端（需 admin 角色）：`/api/v1/admin/dashboard`

## 鉴权方案

- 采用开源 `Sa-Token` 实现登录态与接口鉴权。
- `/api/v1/public/**` 白名单放行，其余 `/api/v1/**` 默认要求登录。
- 管理端接口通过 `@SaCheckRole("admin")` 进行角色鉴权。

## 数据存储

- 使用 MySQL（`localhost:3306`，`sa/123456`）作为主存储。
- 核心表：`sys_user`、`product`、`cart_item`、`orders`、`order_item`。
- 初始化脚本：`sql/init-mall-shop.sql`。
