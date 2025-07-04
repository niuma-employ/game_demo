<template>
  <div>
    <h2>专家列表</h2>
    <el-table :data="experts" style="width: 100%">
      <el-table-column prop="name" label="专家姓名" />
      <el-table-column prop="field" label="领域" />
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'ExpertListView',
  data() {
    return {
      experts: [
        { name: '王五', field: '游戏策划' },
        { name: '赵六', field: '美术设计' }
      ]
    }
  }
}
</script> 