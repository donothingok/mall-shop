<script setup>
import { ref } from 'vue'
import http from '../../api/http'

const address = ref('上海市浦东新区')
const result = ref('')

const createOrder = async () => {
  try {
    const { data } = await http.post('/api/v1/orders', { address: address.value })
    result.value = `下单成功：${data.data.orderNo}`
  } catch (e) {
    result.value = '下单失败，请先登录并加入购物车'
  }
}
</script>

<template>
  <div>
    <p>订单页</p>
    <input v-model="address" placeholder="收货地址" />
    <button @click="createOrder">提交订单</button>
    <p>{{ result }}</p>
  </div>
</template>
