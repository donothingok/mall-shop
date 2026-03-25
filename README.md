# Mall Shop Scaffold

商城系统基础脚手架（前后端分离）：

- `mall-backend`: Spring Boot + Sa-Token + MySQL 后端接口（模块化单体）
- `mall-web-store`: Vue3 前台 H5
- `mall-admin`: Vue3 管理后台
- `mall-mini`: uni-app 小程序
- `docs`: 设计与接口文档
- `sql/init-mall-shop.sql`: MySQL 初始化脚本

## 快速开始

### 0) 初始化数据库
```bash
mysql -h 127.0.0.1 -P 3306 -usa -p123456 < sql/init-mall-shop.sql
```

### 1) 后端
```bash
cd mall-backend
mvn spring-boot:run
```

### 2) 前台 H5
```bash
cd mall-web-store
npm install
npm run dev
```

### 3) 管理后台
```bash
cd mall-admin
npm install
npm run dev
```

### 4) uni-app
使用 HBuilderX 或 `@dcloudio/uni-app` CLI 打开 `mall-mini`。

## 数据库连接

- Host: `localhost`
- Port: `3306`
- User: `sa`
- Password: `123456`
- DB: `mall_shop`

## 后端演示账号

- 用户账号：`user / 123456`
- 管理账号：`admin / 123456`

## 后端演示流程

1. 登录：`POST /api/v1/public/auth/login`
2. 查看商品：`GET /api/v1/public/products`
3. 加入购物车：`POST /api/v1/cart/items`
4. 创建订单：`POST /api/v1/orders`
5. 管理看板：`GET /api/v1/admin/dashboard`（需 admin 角色）
