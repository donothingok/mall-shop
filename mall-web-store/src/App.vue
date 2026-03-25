<template>
  <div>
    <h1>Mall 前台 H5</h1>
    <nav>
      <router-link to="/">首页</router-link> |
      <router-link to="/product">商品</router-link> |
      <router-link to="/cart">购物车</router-link> |
      <router-link to="/mine">我的</router-link>
    </nav>
    <router-view />
  </div>
</template>
