<template>
  <div>
    <h2>开发者列表</h2>
    <el-table v-loading="loading" :data="developers" style="width: 100%">
      <el-table-column prop="devcode" label="账号" />
      <el-table-column prop="devname" label="姓名" />
      <el-table-column prop="devemail" label="邮箱" />
      <el-table-column prop="devinfo" label="简介" />
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
import { getDevList } from '@/api/app'
import { Message } from 'element-ui'

export default {
  name: 'DeveloperListView',
  data() {
    return {
      developers: [],
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
        const res = await getDevList(this.pageNum, this.pageSize)
        if (res.code === 2000) {
          this.developers = res.data.list
          this.total = res.data.total
          this.totalPages = res.data.pages
        } else {
          Message.error(res.mess || '获取开发者列表失败')
        }
      } catch (error) {
        console.error('获取开发者列表失败:', error)
        Message.error('获取开发者列表失败')
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