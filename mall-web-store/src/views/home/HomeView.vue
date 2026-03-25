<script setup>
import { onMounted, ref } from 'vue'
import http from '../../api/http'

const products = ref([])
const error = ref('')

onMounted(async () => {
  try {
    const { data } = await http.get('/api/v1/public/products')
    products.value = data.data || []
  } catch (e) {
    error.value = '加载商品失败'
  }
})
</script>

<template>
  <div>
    <p>首页（商品推荐）</p>
    <p v-if="error">{{ error }}</p>
    <ul>
      <li v-for="item in products" :key="item.id">{{ item.name }} - ¥{{ item.price }}</li>
    </ul>
  </div>
</template>
