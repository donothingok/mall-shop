import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', redirect: '/dashboard' },
  { path: '/dashboard', component: () => import('../views/dashboard/DashboardView.vue') },
  { path: '/product', component: () => import('../views/product/ProductManageView.vue') },
  { path: '/order', component: () => import('../views/order/OrderManageView.vue') },
  { path: '/user', component: () => import('../views/user/UserManageView.vue') },
  { path: '/login', component: () => import('../views/auth/LoginView.vue') }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
