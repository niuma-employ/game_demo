<template>
  <div>
    <h2>app信息</h2>
    <div>软件名称：{{ appInfo.softwarename }}</div>
    <div>apk名称：{{ appInfo.apkname }}</div>
    <h3>版本列表</h3>
    <el-table :data="versionList" style="width: 100%">
      <el-table-column prop="versionno" label="版本号" />
      <el-table-column prop="versioninfo" label="版本介绍" />
      <el-table-column prop="creationdate" label="添加时间">
        <template slot-scope="scope">
          {{ formatDate(scope.row.creationdate) }}
        </template>
      </el-table-column>
      <el-table-column prop="downloadurl" label="下载安装包">
        <template slot-scope="scope">
          <a :href="scope.row.downloadurl" target="_blank">下载地址</a>
        </template>
      </el-table-column>
    </el-table>
    <h3>审核</h3>
    <el-button type="primary" @click="audit(2)">通过</el-button>
    <el-button type="warning" @click="audit(3)">驳回</el-button>
  </div>
</template>

<script>
import { getAppInfoDetail, auditApp } from '@/api/app'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      appInfo: {},
      versionList: []
    }
  },
  created() {
    const appId = this.$route.query.appId
    this.fetchDetail(appId)
  },
  methods: {
    async fetchDetail(appId) {
      const res = await getAppInfoDetail(appId)
      if (res.code === 2000) {
        this.appInfo = res.data.appInfo
        this.versionList = res.data.versionList
      }
    },
    async audit(status) {
      const res = await auditApp(this.appInfo.id, status)
      if (res.code === 2000) {
        Message.success('操作成功')
        this.$router.back()
      }
    },
    formatDate(date) {
      if (!date) return ''
      return new Date(date).toLocaleString()
    }
  }
}
</script> 