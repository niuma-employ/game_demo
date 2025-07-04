<template>
  <div style="width: 400px; margin: 60px auto;">
    <el-card>
      <h2 style="text-align:center;">普通用户注册</h2>
      <el-form :model="form" ref="form" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="form.userCode" />
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.userName" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.userPassword" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.userEmail" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { normalUserRegisterApi } from '@/api/app';
import { Message } from 'element-ui';
export default {
  name: 'NormalUserRegisterView',
  data() {
    return {
      form: {
        userCode: '',
        userName: '',
        userPassword: '',
        userEmail: ''
      }
    }
  },
  methods: {
    onRegister() {
      normalUserRegisterApi(this.form).then(res => {
        if(res === 'success') {
          Message.success('注册成功，请登录！');
          this.$router.push('/');
        } else {
          Message.error(res);
        }
      })
    }
  }
}
</script> 