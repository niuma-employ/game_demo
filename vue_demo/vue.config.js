module.exports = {
  devServer:{
    host:'localhost',
    port:8080, //前端项目端口号
    open: true,
    proxy:{
      [process.env.VUE_APP_BASE_API]: {
        target: `http://localhost:8888`, //后端项目地址
        changeOrigin: true,
        pathRewrite: {['^' + process.env.VUE_APP_BASE_API]: ''}
      }
    }
  }
}