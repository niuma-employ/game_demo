<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-aside  width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu
          router
          :default-active="$route.path"
          :default-openeds="['1','2']">
        <!-- 开发者菜单 -->
        <template v-if="userType === 'dev'">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-s-operation"></i>手游管理</template>
            <el-menu-item index="/appInfoDev"><i class="el-icon-menu"></i>我的手游</el-menu-item>
          </el-submenu>
        </template>

        <!-- 管理员菜单 -->
        <template v-if="userType === 'admin'">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-s-operation"></i>手游管理</template>
            <el-menu-item index="/appInfoAdmin"><i class="el-icon-menu"></i>手游列表</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-user"></i>用户管理</template>
            <el-menu-item index="/user-list"><i class="el-icon-user"></i>用户列表</el-menu-item>
            <el-menu-item index="/developer-list"><i class="el-icon-user"></i>开发员列表</el-menu-item>
            <el-menu-item index="/admin-list"><i class="el-icon-user"></i>管理员列表</el-menu-item>
          </el-submenu>
        </template>

        <!-- 普通用户菜单 -->
        <template v-if="userType === 'user'">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-s-operation"></i>手游中心</template>
            <el-menu-item index="/appInfoUser"><i class="el-icon-menu"></i>手游列表</el-menu-item>
            <el-menu-item index="/myCollection"><i class="el-icon-star-on"></i>我的收藏</el-menu-item>
          </el-submenu>
        </template>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>{{username}}</span>
      </el-header>

      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.el-header {
  background-color: #409EFF;
  color: #fff;
  line-height: 60px;
  box-shadow: 0 2px 8px #f0f1f2;
}

.el-aside {
  color: #333;
  background: linear-gradient(180deg, #f8fafc 0%, #e3e8ee 100%);
  box-shadow: 2px 0 8px #f0f1f2;
}

.el-menu {
  border-right: none;
  background: transparent;
}

.el-menu-item, .el-submenu__title {
  border-radius: 6px;
  margin: 4px 8px;
  transition: background 0.2s;
}

.el-menu-item:hover, .el-submenu__title:hover {
  background: #e6f7ff !important;
}

.el-menu-item.is-active {
  background: #409EFF !important;
  color: #fff !important;
}
</style>

<script>
export default {
  data() {
    return {
      username: '',
      userType: ''
    }
  },
  methods:{
    logout(){
      sessionStorage.clear();
      this.$router.push("/")
    }
  },
  created() {
    this.username = sessionStorage.getItem("username")
    this.userType = sessionStorage.getItem("userType")
    console.log('当前用户类型：', this.userType)
  },
  watch: {
    '$route'() {
      this.username = sessionStorage.getItem("username")
      this.userType = sessionStorage.getItem("userType")
      console.log('路由变化，当前用户类型：', this.userType)
    }
  },
  mounted() {
    this.username = sessionStorage.getItem("username")
    this.userType = sessionStorage.getItem("userType")
  }
};
</script>