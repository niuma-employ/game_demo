<template>
  <div>
    <el-descriptions title="app信息">
      <el-descriptions-item label="软件名称">{{appInfo.softwarename}}</el-descriptions-item>
      <el-descriptions-item label="apk名称">{{ appInfo.apkname }}</el-descriptions-item>
    </el-descriptions>
    <hr>
    <el-descriptions title="版本列表">

    </el-descriptions>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="versionno"
          label="版本号">
      </el-table-column>
      <el-table-column
          prop="versioninfo"
          label="版本介绍">
      </el-table-column>
      <el-table-column
          prop="creationdate"
          label="添加时间">
      </el-table-column>
      <el-table-column
          label="下载安装包">
        <template slot-scope="scope">
          <a v-if="scope.row.downloadlink" :href="baseURL + '/' + scope.row.downloadlink" target="_blank">下载地址</a>
          <span v-else>暂无</span>
        </template>
      </el-table-column>
    </el-table>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="版本号" prop="name">
        <el-input v-model="ruleForm.versionno"></el-input>
      </el-form-item>
      <el-form-item label="版本介绍" prop="name">
        <el-input v-model="ruleForm.versioninfo"></el-input>
      </el-form-item>
      <el-form-item label="版本大小" prop="name">
        <el-input v-model="ruleForm.versionsize"></el-input>
      </el-form-item>
      <el-form-item label="apk文件" prop="apkFile">
        <el-upload
            class="upload-demo"
            action="#"
            :on-change="changeUpload"
            :before-upload="beforeUpload"
            :auto-upload="false"
            multiple
            :limit="1">
          <el-button size="small" type="primary">选取文件</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import {addVersion, getAppWithVersion} from "@/api/app";

export default {
  data(){
    return{
      ruleForm:{
        appid:'',
        versionno:'',
        versioninfo:'',
        versionsize:'',
        apkFile:''
      },
      rules:{
        versionno: [
          { required: true, message: '请输入版本号', trigger: 'blur' },
        ],
        versioninfo: [
          { required: true, message: '请输入版本介绍', trigger: 'blur' },
        ],
        versionsize: [
          { required: true, message: '请输入版大小', trigger: 'blur' },
        ],
        apkFile: [
          { required: true, message: '请选择apk', trigger: 'blur' },
        ],
      },
      tableData:[],
      appid:'',
      appInfo:{
        id:'',
        softwarename:'',
        apkname:''
      },
      baseURL: process.env.VUE_APP_BASE_API || ''
    }
  },
  methods:{
    changeUpload(file){
      this.ruleForm.apkFile = file.raw
    },
    beforeUpload(file) {
      const isLt500M = file.size / 1024 / 1024 < 500;
      if (!isLt500M) {
        this.$message.error('上传文件大小不能超过 500MB!');
      }
      return isLt500M;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var data = this.ruleForm

          //组装一个formdata
          var formdata = new FormData
          for(let key in data){
            formdata.append(key,data[key])
          }
          addVersion(formdata).then(res=>{
            console.log(res)
            this.$message.success("添加成功")
            this.getApp()
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    getApp(){
      getAppWithVersion(this.appInfo.id).then(res=>{
        console.log(res)
        this.appInfo = res.data
        this.tableData = res.data.versions
      })
    }
  },
  created() {
    this.appInfo.id = this.$route.query.appid
    this.ruleForm.appid=this.$route.query.appid
    this.getApp();

  }
}
</script>
