<template>
  <div>
    <h2>我的收藏</h2>
    <el-table :data="collections" style="width: 100%">
      <el-table-column prop="appName" label="软件名称" />
      <el-table-column prop="apkName" label="apk名称" />
      <el-table-column label="logo">
        <template slot-scope="scope">
          <img :src="baseURL + '/' + scope.row.logo" style="height:40px;width:40px" />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="danger" @click="uncollect(scope.row.appId)">取消收藏</el-button>
          <el-button type="primary" @click="download(scope.row.downloadUrl)">下载</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getMyCollections, uncollectApp } from '@/api/app';

export default {
  name: 'MyCollectionView',
  data() {
    return {
      collections: [],
      baseURL: process.env.VUE_APP_BASE_API || ''
    }
  },
  methods: {
    getCollections() {
      getMyCollections().then(res => {
        console.log('getMyCollections 返回:', res);
        if(res.code === 2000) {
          this.collections = res.data.map(item => ({
            appName: item.softwarename,
            apkName: item.apkname,
            logo: item.logopicpath,
            appId: item.id,
            downloadUrl: item.downloadUrl
          }));
        }
      })
    },
    uncollect(appId) {
      uncollectApp(appId).then(() => {
        this.$message.success('取消收藏成功');
        this.getCollections();
      })
    },
    download(url) {
      window.open(this.baseURL + '/' + url, '_blank');
    }
  },
  mounted() {
    this.getCollections();
    this.$root.$on('refreshCollection', this.getCollections);
  },
  beforeDestroy() {
    this.$root.$off('refreshCollection', this.getCollections);
  }
}
</script> 