<template>
  <div class="about">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="软件名称">
        <el-input v-model="formInline.softwarename" placeholder="审批人"></el-input>
      </el-form-item>
      <el-form-item label="apk名称">
        <el-input v-model="formInline.apkname" placeholder="审批人"></el-input>
      </el-form-item>
      <el-form-item>
        <el-form-item label="一级分类">
          <el-select @change="getLevel2" v-model="formInline.categorylevel1" placeholder="请选择一级分类">
            <el-option label="请选择一级分类" value=""></el-option>
            <el-option v-for="c1 in level1" :key="c1.id" :label="c1.categoryname" :value="c1.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="二级分类">
          <el-select @change="getLevel3" v-model="formInline.categorylevel2" placeholder="请选择二级分类">
            <el-option label="请选择二级分类" value=""></el-option>
            <el-option v-for="c2 in level2" :key="c2.id" :label="c2.categoryname" :value="c2.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="三级分类">
          <el-select v-model="formInline.categorylevel3" placeholder="请选择三级分类">
            <el-option label="请选择三级分类" value=""></el-option>
            <el-option v-for="c3 in level3" :key="c3.id" :label="c3.categoryname" :value="c3.id"></el-option>
          </el-select>
        </el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button v-if="role==='dev'" type="primary" @click="onAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="softwarename"
          label="软件名称">
      </el-table-column>
      <el-table-column
          prop="apkname"
          label="apk名称">
      </el-table-column>
      <el-table-column
          label="logo图片">
        <template slot-scope="scope">
          <img :src="baseURL+'/'+scope.row.logopicpath" alt="" style="height: 50px;width: 50px">
        </template>
      </el-table-column>
      <el-table-column
          v-if="role==='admin'"
          prop="devName"
          label="开发者名称">
      </el-table-column>
      <el-table-column
          label="分类">
        <template slot-scope="scope">
          {{scope.row.level1Name}}->{{scope.row.level2Name}}->{{scope.row.level3Name}}
        </template>
      </el-table-column>
      <el-table-column
          label="状态">
        <template slot-scope="scope">
          {{scope.row.status ==1? '待审核':scope.row.status ==2? '审核通过':scope.row.status ==3? '审核未通过':scope.row.status ==4?  '已上架':'已下架'}}
        </template>
      </el-table-column>
      <el-table-column
          label="操作">
        <template slot-scope="scope">
          <template v-if="role==='dev'">
            <el-dropdown>
              <el-button type="primary">
                更多操作<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="toUpdate(scope.row)">修改</el-dropdown-item>
                <el-dropdown-item @click.native="toAddVersion(scope.row.id)">添加版本</el-dropdown-item>
                <el-dropdown-item @click.native="toDel(scope.row.id)">删除</el-dropdown-item>
                <el-dropdown-item v-if="scope.row.status===2" @click.native="onPublish(scope.row.id)">上架</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

            <el-upload
                class="upload-demo"
                name="logo"
                :before-upload="beforeUpload"
                accept="image/*"
                show-file-list="false"
                :file-list="fileList"
                :on-success="uploadSuccess"
                :action="baseURL+'/appInfo/upLogo/'+scope.row.id"
                :headers="headerObj"
                :limit="1">
              <el-button size="small" type="warning">上传logo</el-button>
            </el-upload>
          </template>
          <template v-else-if="role==='user'">
            <el-button type="primary" @click="onCollect(scope.row.id)">收藏</el-button>
            <el-button type="info" @click="onDetail(scope.row.id)">查看详情</el-button>
          </template>
          <template v-else-if="role==='admin'">
            <el-button type="primary" v-if="scope.row.status===1" @click="goAudit(scope.row)">审核</el-button>
            <el-button type="info" @click="onDetail(scope.row.id)">查看详情</el-button>
          </template>
        </template>
      </el-table-column>
      </el-table>
    <el-pagination
        background
        :current-page="pageNum"
        :page-size="5"
        @current-change="changePageNum"
        :page-count="pages"
        layout="prev, pager, next"
        :total="total">
    </el-pagination>
    <el-dialog
        title="新增/修改"
        :visible.sync="dialogVisible"
        destroy-on-close="true"
        width="60%"
       >
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="8">
            <el-form-item label="软件名称" prop="softwarename">
              <el-input v-model="ruleForm.softwarename"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="apk名称" prop="apkname">
              <el-input v-model="ruleForm.apkname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属平台" prop="flatformid">
              <el-select v-model="ruleForm.flatformid" placeholder="请选择所属平台">
                <el-option label="手机" :value="1"></el-option>
                <el-option label="平板" :value="2"></el-option>
                <el-option label="通用" :value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="一级分类">
              <el-select @change="getLevel22" v-model="ruleForm.categorylevel1" placeholder="请选择一级分类">
                <el-option label="请选择一级分类" value=""></el-option>
                <el-option v-for="c1 in addlevel1" :key="c1.id" :label="c1.categoryname" :value="c1.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级分类">
              <el-select @change="getLevel33" v-model="ruleForm.categorylevel2" placeholder="请选择二级分类">
                <el-option label="请选择二级分类" value=""></el-option>
                <el-option v-for="c2 in addlevel2" :key="c2.id" :label="c2.categoryname" :value="c2.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="三级分类">
              <el-select v-model="ruleForm.categorylevel3" placeholder="请选择三级分类">
                <el-option label="请选择三级分类" value=""></el-option>
                <el-option v-for="c3 in addlevel3" :key="c3.id" :label="c3.categoryname" :value="c3.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="简介" prop="appinfo">
              <el-input type="textarea" v-model="ruleForm.appinfo"></el-input>
            </el-form-item>

          </el-col>
          <el-col :span="8">
            <el-form-item label="软件大小" prop="softwaresize">
              <el-input v-model="ruleForm.softwaresize"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
import {delApp, getAppInfoPage, getCateoryTree, saveOrUpdateApp, validateApkName, collectApp, publishApp, auditApp} from "@/api/app";


export default {
  name:'AppView',
  props: {
    role: {
      type: String,
      default: 'dev'
    }
  },
  data(){
    var validateApkname = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入apk名称'));
      }else {
        //axios请求校验唯一性
        validateApkName(value,this.ruleForm.id).then(response => {
          if(response.data){
            callback();
          }else {
            callback(new Error('apk名称已占用'));
          }
        });
      }
    };
    return{
      headerObj:{
        token:sessionStorage.getItem("token")
      },
      fileList:[],
      baseURL:process.env.VUE_APP_BASE_API,
      dialogVisible: false,
      ruleForm:{
        id:'',
        softwarename:'',
        apkname:'',
        flatformid:'',//所属平台
        categorylevel1:'',
        categorylevel2:'',
        categorylevel3:'',
        softwaresize:'',
        appinfo:'',
      },

      rules:{
        softwarename: [
          { required: true, message: '请输入软件名称', trigger: 'blur' },
        ],
        apkname: [
          { required: true, message: '请输入apk名称', trigger: 'blur' },
          { validator: validateApkname, trigger: 'blur' }
        ],
        softwaresize: [
          { required: true, message: '请输入软件大小', trigger: 'blur' },
          { pattern: /^(?!0+(\.0*)?$)(\+)?([1-9]\d*(\.\d+)?)$/,message:'必须是正数', trigger: 'blur' }
        ],
        categorylevel1: [
          { required: true, message: '请选择一级分类', trigger: 'change' }
        ],
        categorylevel2: [
          { required: true, message: '请选择二级分类', trigger: 'change' }
        ],
        categorylevel3: [
          { required: true, message: '请选择三级分类', trigger: 'change' }
        ],
      },

      level1:[],
      level2:[],
      level3:[],

      addlevel1:[],
      addlevel2:[],
      addlevel3:[],

      tableData:[],
      pages:0,
      total:0,
      pageNum:1,
      formInline:{
        softwarename:'',
        apkname:'',
        categorylevel1:'',
        categorylevel2:'',
        categorylevel3:''
      }
    }
  },

  methods:{
    toAddVersion(id){
      console.log(id)
      this.$router.push({path: '/version',query:{appid: id}})
    },
    beforeUpload(file){
      const isImage = file.type.startsWith('image/');
      if(!isImage){
        this.$message.error('只能上传图片')
      }
      return isImage;
    },
    uploadSuccess(response,file,fileList){
      console.log(response);
      console.log(file);
      console.log(fileList);

      //清空fileList
      this.fileList=[]
      this.getPage()
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //说明用户按规矩填写
          saveOrUpdateApp(this.ruleForm).then(() => {
            this.dialogVisible=false;
            this.getPage();
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

    onAdd(){
      this.ruleForm={
        id:'',
        softwarename:'',
        apkname:'',
        flatformid:'',//所属平台
        categorylevel1:'',
        categorylevel2:'',
        categorylevel3:'',
        softwaresize:'',
        appinfo:'',
      }
      this.pageNum = 1;
      this.dialogVisible = true;
    },

    getLevel2(){
      //先清空二级和三级
      this.level2 = []
      this.level3 = []
      this.formInline.categorylevel2=''
      this.formInline.categorylevel3=''

      //根据选择1级，加载二级分类列表
      var one = this.formInline.categorylevel1

      var data = this.level1
      for(let i = 0;i < data.length;i++){
        if (one == data[i].id){
          this.level2=data[i].children
        }
      }
    },
    getLevel3(){
      //先清空三级
      this.level3 = []
      this.formInline.categorylevel3=''
      //根据选择2级，加载三级分类列表
      var two = this.formInline.categorylevel2

      var data = this.level2
      for(let i = 0;i < data.length;i++){
        if (two == data[i].id){
          this.level3=data[i].children
        }
      }

    },

    getLevel22(){
      //先清空二级和三级
      this.addlevel2 = []
      this.addlevel3 = []
      this.ruleForm.categorylevel2=''
      this.ruleForm.categorylevel3=''

      //根据选择1级，加载二级分类列表
      var one = this.ruleForm.categorylevel1

      var data = this.addlevel1
      for(let i = 0;i < data.length;i++){
        if (one == data[i].id){
          this.addlevel2=data[i].children
        }
      }
    },
    getLevel33(){
      //先清空三级
      this.addlevel3 = []
      this.ruleForm.categorylevel3=''
      //根据选择2级，加载三级分类列表
      var two = this.ruleForm.categorylevel2

      var data = this.addlevel2
      for(let i = 0;i < data.length;i++){
        if (two == data[i].id){
          this.addlevel3=data[i].children
        }
      }

    },


    changePageNum(value){
      this.pageNum=value;
      this.getPage();
    },
    getPage(){
      getAppInfoPage(this.formInline,this.pageNum).then(response => {
        if(response.code == 2000){
          this.tableData = response.data.list;

          console.log(this.tableData)
          this.pages = response.data.pages;
          this.total = response.data.total;
          this.pageNum = response.data.pageNum;
        }
      });
    },

    toUpdate(row){
      this.ruleForm.id = row.id
      this.ruleForm.appinfo = row.appinfo
      this.ruleForm.softwarename = row.softwarename
      this.ruleForm.apkname = row.apkname
      this.ruleForm.softwaresize = row.softwaresize
      this.ruleForm.categorylevel1 = row.categorylevel1
      this.getLevel22(),
      this.ruleForm.categorylevel2 = row.categorylevel2
      this.getLevel33(),
      this.ruleForm.categorylevel3 = row.categorylevel3
      this.ruleForm.flatformid = row.flatformid

      this.dialogVisible=true
    },
    toDel(id){
      //防止误点
      console.log(id)
      this.$confirm('此操作将永久删除该app, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        delApp(id).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });

          this.getPage()
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    getTree(){
      getCateoryTree().then(response => {
        this.level1 = response.data.children;
        this.addlevel1 = response.data.children;
        console.log( response.data)
      });
    },
    onSubmit() {
      this.pageNum=1
      this.getPage()

    },
    onCollect(appId) {
      collectApp(appId).then(() => {
        this.$message.success('收藏成功');
        // 自动刷新"我的收藏"页面
        if (this.$route.path === '/myCollection') {
          this.$router.replace({ path: '/myCollection', query: { t: Date.now() } });
        } else {
          // 通过全局事件通知刷新
          this.$root.$emit('refreshCollection');
        }
      })
    },
    onPublish(appId) {
      publishApp(appId).then(() => {
        this.$message.success('上架成功');
        this.getPage();
      })
    },
    onAudit(appId, status) {
      auditApp(appId, status).then(() => {
        this.$message.success('审核成功');
        this.getPage();
      })
    },
    onDetail(appId) {
      // 跳转到详情页，或弹窗展示详情
      this.$router.push({ path: '/appInfoDetail', query: { id: appId } });
    },
    goAudit(row) {
      this.$router.push({ path: '/appAudit', query: { appId: row.id } })
    },
  },
  created() {
    this.getPage()

    this.getTree()

  }
}
</script>