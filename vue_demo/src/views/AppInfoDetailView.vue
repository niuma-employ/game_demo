<template>
  <div>
    <h2>手游详情</h2>
    <div v-if="loading">加载中...</div>
    <div v-else-if="detail">
      <p><strong>软件名称：</strong>{{ detail.softwarename }}</p>
      <p><strong>apk名称：</strong>{{ detail.apkname }}</p>
      <p><strong>简介：</strong>{{ detail.appinfo }}</p>
      <p><strong>软件大小：</strong>{{ detail.softwaresize }}</p>
      <p><strong>分类：</strong>{{ detail.level1Name }} -> {{ detail.level2Name }} -> {{ detail.level3Name }}</p>
      <img v-if="detail.logopicpath" :src="baseURL + '/' + detail.logopicpath" style="height:80px;width:80px" />
      
      <!-- 版本信息 -->
      <div v-if="detail.versions && detail.versions.length > 0">
        <h3>版本历史</h3>
        <el-table :data="detail.versions" style="width: 100%">
          <el-table-column prop="versionno" label="版本号" />
          <el-table-column prop="versioninfo" label="版本信息" />
          <el-table-column prop="publishstatus" label="状态">
            <template slot-scope="scope">
              {{ scope.row.publishstatus === 1 ? '已发布' : '未发布' }}
            </template>
          </el-table-column>
          <el-table-column label="下载">
            <template slot-scope="scope">
              <el-button 
                type="primary" 
                size="small" 
                v-if="scope.row.downloadlink"
                @click="download(scope.row.downloadlink)">
                下载
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div v-else>未找到详情</div>
  </div>
</template>

<script>
import { getAppDetail } from '@/api/app';

export default {
  name: 'AppInfoDetailView',
  data() {
    return {
      detail: null,
      loading: true,
      baseURL: process.env.VUE_APP_BASE_API || ''
    }
  },
  methods: {
    download(url) {
      window.open(this.baseURL + '/' + url, '_blank');
    }
  },
  created() {
    const appId = this.$route.query.id;
    if (appId) {
      getAppDetail(appId).then(res => {
        if (res.code === 2000) {
          this.detail = res.data;
        }
        this.loading = false;
      }).catch(() => {
        this.loading = false;
      });
    } else {
      this.loading = false;
    }
  }
}
</script>

<style scoped>
.el-table {
  margin-top: 20px;
}
</style> 