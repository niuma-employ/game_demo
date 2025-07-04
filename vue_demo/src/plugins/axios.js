"use strict";

import Vue from 'vue';
import axios from "axios";
import {Message, MessageBox} from "element-ui";

// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
  // baseURL: process.env.baseURL || process.env.apiUrl || ""
  // timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
  baseURL: process.env.VUE_APP_BASE_API
};

const _axios = axios.create(config);

//请求拦截器 统一把token带到后端
_axios.interceptors.request.use(
  function(config) {
    // Do something before request is sent
    var token = sessionStorage.getItem("token");
    if(token){
      config.headers['token'] =token
    }
    return config;
  },
  function(error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

//响应拦截器
// Add a response interceptor
_axios.interceptors.response.use(
  function(response) {
    // Do something with response data
    if(response.data.code==3000){
      MessageBox.confirm(response.data.mess,"系统提示",{
          confirmButtonText:'重新登录',
          cancelButtonText:'取消',
          type:'warning'
        }
    ).then(()=>{
      location.href="/";

    })
      //阻止进一步链式调用，没任何提示信息 不走then
      return new Promise(()=>{})

    }else if(response.data.code==5000){
      Message.error(response.data.mess)
      //阻止进一步链式调用，没任何提示信息 不走then
      return new Promise(()=>{})
    }else {

        return response.data;
    }
  },
  function(error) {
    // Do something with response error
    return Promise.reject(error);
  }
);

Plugin.install = function(Vue) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    },
  });
};

Vue.use(Plugin)

export default Plugin;
export {_axios as request}


