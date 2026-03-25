<script setup>
import { onMounted, ref } from 'vue'
import http from '../../api/http'

const data = ref(null)
const tip = ref('')

onMounted(async () => {
  try {
    const res = await http.get('/api/v1/admin/dashboard')
    data.value = res.data.data
  } catch (e) {
    tip.value = '加载失败，请先用 admin 登录'
  }
})
</script>

<template>
  <div>
    <p>数据看板</p>
    <p v-if="tip">{{ tip }}</p>
    <pre v-else>{{ data }}</pre>
  </div>
</template>
