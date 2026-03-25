<script setup>
import { ref } from 'vue'
import http from '../../api/http'

const productId = ref(1)
const quantity = ref(1)
const result = ref('')

const addCart = async () => {
  try {
    await http.post('/api/v1/cart/items', { productId: Number(productId.value), quantity: Number(quantity.value) })
    result.value = '加入购物车成功'
  } catch (e) {
    result.value = '加入失败，请先登录'
  }
}
</script>

<template>
  <div>
    <p>购物车</p>
    <input v-model="productId" type="number" placeholder="商品ID" />
    <input v-model="quantity" type="number" placeholder="数量" />
    <button @click="addCart">加入购物车</button>
    <p>{{ result }}</p>
  </div>
</template>
