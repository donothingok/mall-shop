import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', component: () => import('../views/home/HomeView.vue') },
  { path: '/product', component: () => import('../views/product/ProductView.vue') },
  { path: '/cart', component: () => import('../views/cart/CartView.vue') },
  { path: '/order', component: () => import('../views/order/OrderView.vue') },
  { path: '/mine', component: () => import('../views/mine/MineView.vue') }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
