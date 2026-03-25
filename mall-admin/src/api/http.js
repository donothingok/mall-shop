import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

http.interceptors.request.use((config) => {
  const token = localStorage.getItem('admin_token')
  if (token) config.headers.satoken = token
  return config
})

export default http
