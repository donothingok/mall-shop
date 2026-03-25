# Mall Shop 架构说明

## 模块划分

- 用户与认证：`modules/user`, `modules/auth`
- 商品：`modules/product`
- 订单：`modules/order`
- 管理端：`modules/admin`
- 公共能力：`common`

## 核心约定

- API 前缀：`/api/v1`
- 统一响应：`ApiResponse<T>`
- 认证：预留 JWT 过滤器位
- 后续扩展：Redis、MQ、支付回调、库存预占
