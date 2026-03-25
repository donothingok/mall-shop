<script setup>
import { ref } from 'vue'
import http from '../../api/http'

const username = ref('user')
const password = ref('123456')
const stateText = ref('未登录')

const login = async () => {
  try {
    const { data } = await http.post('/api/v1/public/auth/login', { username: username.value, password: password.value })
    localStorage.setItem('mall_token', data.data.token)
    stateText.value = `登录成功：${data.data.username}`
  } catch (e) {
    stateText.value = '登录失败'
  }
}
</script>

<template>
  <div>
    <p>我的页面</p>
    <input v-model="username" placeholder="用户名" />
    <input v-model="password" placeholder="密码" />
    <button @click="login">登录</button>
    <p>{{ stateText }}</p>
  </div>
</template>
