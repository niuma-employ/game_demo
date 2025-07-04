import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from "@/views/LoginView.vue";
import AppInfoView from "@/views/AppInfoView.vue";
import commonView from "@/views/CommonView.vue";
import AboutView from "@/views/AboutView.vue";
import VersionView from "@/views/VersionView.vue";
import UserListView from "@/views/UserListView.vue";
import ExpertListView from "@/views/ExpertListView.vue";
import DeveloperListView from "@/views/DeveloperListView.vue";
import AdminListView from "@/views/AdminListView.vue";
import NormalUserRegisterView from "@/views/NormalUserRegisterView.vue";
import AppInfoDevView from "@/views/AppInfoDevView.vue";
import AppInfoUserView from "@/views/AppInfoUserView.vue";
import AppInfoAdminView from "@/views/AppInfoAdminView.vue";
import MyCollectionView from "@/views/MyCollectionView.vue";
import AppAuditView from "@/views/AppAuditView.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/appInfo',
    name: 'appInfo',
    component: commonView,
    children:[{path:'',component: AppInfoView}]
  },
  {
    path: '/about',
    name: 'about',
    component: commonView,
    children:[{path:'',component: AboutView}]
  },
  {
    path: '/version',
    name: 'version',
    component: commonView,
    children:[{path:'',component: VersionView}]
  },
  {
    path: '/user-list',
    name: 'user-list',
    component: commonView,
    children:[{path:'',component: UserListView}]
  },
  {
    path: '/expert-list',
    name: 'expert-list',
    component: commonView,
    children:[{path:'',component: ExpertListView}]
  },
  {
    path: '/developer-list',
    name: 'developer-list',
    component: commonView,
    children:[{path:'',component: DeveloperListView}]
  },
  {
    path: '/admin-list',
    name: 'admin-list',
    component: commonView,
    children:[{path:'',component: AdminListView}]
  },
  {
    path: '/normal-user-register',
    name: 'normal-user-register',
    component: NormalUserRegisterView
  },
  {
    path: '/appInfoDev',
    name: 'appInfoDev',
    component: commonView,
    children: [{ path: '', component: AppInfoDevView }]
  },
  {
    path: '/appInfoUser',
    name: 'appInfoUser',
    component: commonView,
    children: [{ path: '', component: AppInfoUserView }]
  },
  {
    path: '/appInfoAdmin',
    name: 'appInfoAdmin',
    component: commonView,
    children: [{ path: '', component: AppInfoAdminView }]
  },
  {
    path: '/myCollection',
    name: 'myCollection',
    component: commonView,
    children: [{ path: '', component: MyCollectionView }]
  },
  {
    path: '/appInfoDetail',
    name: 'appInfoDetail',
    component: commonView,
    children: [{ path: '', component: () => import('@/views/AppInfoDetailView.vue') }]
  },
  {
    path: '/appAudit',
    name: 'appAudit',
    component: AppAuditView
  },
]

const router = new VueRouter({
  routes
})

export default router
