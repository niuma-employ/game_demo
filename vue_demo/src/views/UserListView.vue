<template>
  <div>
    <h2>用户列表</h2>
    <el-table :data="users" style="width: 100%">
      <el-table-column prop="userCode" label="账号" />
      <el-table-column prop="userName" label="昵称" />
      <el-table-column prop="userEmail" label="邮箱" />
      <el-table-column prop="userInfo" label="简介" />
      <el-table-column prop="creationDate" label="注册时间" />
    </el-table>
    <el-pagination
      background
      :current-page="pageNum"
      :page-size="pageSize"
      @current-change="handleCurrentChange"
      :page-count="totalPages"
      layout="prev, pager, next"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import { getNormalUserList } from '@/api/app'

export default {
  name: 'UserListView',
  data() {
    return {
      users: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      totalPages: 0
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      try {
        const res = await getNormalUserList(this.pageNum, this.pageSize)
        this.users = res.data.list
        this.total = res.data.total
        this.totalPages = res.data.pages
      } catch (error) {
        console.error('获取用户列表失败:', error)
      }
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.fetchData()
    }
  }
}
</script> 