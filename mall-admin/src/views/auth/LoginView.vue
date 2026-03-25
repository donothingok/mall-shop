<script setup>
import { ref } from 'vue'
import http from '../../api/http'

const username = ref('admin')
const password = ref('123456')
const tip = ref('')

const login = async () => {
  try {
    const { data } = await http.post('/api/v1/public/auth/login', { username: username.value, password: password.value })
    localStorage.setItem('admin_token', data.data.token)
    tip.value = '登录成功'
  } catch (e) {
    tip.value = '登录失败'
  }
}
</script>

<template>
  <div>
    <p>管理员登录</p>
    <input v-model="username" placeholder="admin" />
    <input v-model="password" placeholder="password" />
    <button @click="login">登录</button>
    <p>{{ tip }}</p>
  </div>
</template>
