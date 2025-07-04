<template>
  <div>
    <h2>管理员列表</h2>
    <el-table v-loading="loading" :data="admins" style="width: 100%">
      <el-table-column prop="usercode" label="账号" />
      <el-table-column prop="username" label="姓名" />
      <el-table-column prop="usertype" label="角色">
        <template slot-scope="scope">
          {{ scope.row.usertype === 1 ? '超级管理员' : '普通管理员' }}
        </template>
      </el-table-column>
      <el-table-column prop="creationdate" label="创建时间">
        <template slot-scope="scope">
          {{ formatDate(scope.row.creationdate) }}
        </template>
      </el-table-column>
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
import { getAdminList } from '@/api/app'
import { Message } from 'element-ui'

export default {
  name: 'AdminListView',
  data() {
    return {
      admins: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      totalPages: 0,
      loading: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    formatDate(date) {
      if (!date) return ''
      return new Date(date).toLocaleString()
    },
    async fetchData() {
      this.loading = true
      try {
        const res = await getAdminList(this.pageNum, this.pageSize)
        if (res.code === 2000) {
          this.admins = res.data.list
          this.total = res.data.total
          this.totalPages = res.data.pages
        } else {
          Message.error(res.mess || '获取管理员列表失败')
        }
      } catch (error) {
        console.error('获取管理员列表失败:', error)
        Message.error('获取管理员列表失败')
      } finally {
        this.loading = false
      }
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.fetchData()
    }
  }
}
</script> 