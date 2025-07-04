<template>
  <div>
    <el-dialog
        title="欢迎登录系统"
        :show-close="false"
        :close-on-press-escape="false"
        :close-on-click-modal="false"
        :visible.sync="dialogVisible"
        width="60%"
        >
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>

        <el-form-item label="账户类型">
          <el-radio-group v-model="form.userType">
            <el-radio label="admin">管理员</el-radio>
            <el-radio label="dev">开发者</el-radio>
            <el-radio label="normal">普通用户</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即登录</el-button>
          <el-button @click="onCancel">取消</el-button>
          <el-button type="success" @click="goRegister" v-if="form.userType==='normal'">普通用户注册入口</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>

import {loginApi} from "@/api/app";
import {Message} from "element-ui";

export default {
  name: 'LoginView',
  data(){
    return{
      dialogVisible:true,
      form:{
        username:'test001',
        password:'123456',
        userType:'dev'
      }
    }
  },
  methods:{
    onSubmit(){
      loginApi(this.form).then(res=>{
        // 假设后端返回 { code, mess, data: token, userType }
        const token = res.data;
        let userType = this.form.userType;
        if (userType === 'normal') userType = 'user';
        Message.success(res.mess);
        sessionStorage.setItem("token", token);
        sessionStorage.setItem("username", this.form.username);
        sessionStorage.setItem("userType", userType);
        if (userType === 'admin') {
          this.$router.push('/appInfoAdmin');
        } else if (userType === 'dev') {
          this.$router.push('/appInfoDev');
        } else if (userType === 'user') {
          this.$router.push('/appInfoUser');
        }
      })
    },
    onCancel(){
      this.dialogVisible = false;
    },
    goRegister(){
      this.$router.push('/normal-user-register');
    }
  }
}
</script>
