import {request} from '@/plugins/axios'


export function getAppInfoPage(data,pageNum) {
    return request({
        url: '/appInfo/page?pageNum='+pageNum,
        method: 'post',
        data: data,
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

export function getCateoryTree() {
    return request({
        url: '/appCateory/tree',
        method: 'get',
    })
}

export function validateApkName(apkname,id) {
    return request({
        url: '/appInfo/validateApkName?apkname='+apkname+"&id="+id,
        method: 'get',
    })
}

//新增或者修改
export function saveOrUpdateApp(data) {
    return request({
        url: '/appInfo/add',
        method: 'post',
        data: data,
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

export function getAppWithVersion(appid) {
    return request({
        url: '/appInfo/appWithVersion/'+appid,
        method: 'get',
    })
}

export function addVersion(formData) {
    return request({
        url: '/version/',
        method: 'post',
        data: formData
    })
}

export function delApp(id) {
    return request({
        url: '/appInfo/'+id,
        method: 'delete'
    })
}

export function loginApi(data) {
    return request({
        url: '/login',
        method: 'post',
        data:data
    })
}

export function normalUserRegisterApi(data) {
    return request({
        url: '/normalUser/register',
        method: 'post',
        data: data
    })
}

export function normalUserLoginApi(data) {
    return request({
        url: '/normalUser/login',
        method: 'post',
        data: data
    })
}

export function collectApp(appId) {
    return request({
        url: `/appInfo/collect/${appId}`,
        method: 'post',
        headers: { token: sessionStorage.getItem('token') }
    })
}

export function uncollectApp(appId) {
    return request({
        url: `/appInfo/uncollect/${appId}`,
        method: 'post',
        headers: { token: sessionStorage.getItem('token') }
    })
}

export function getMyCollections() {
    return request({
        url: '/appInfo/myCollections',
        method: 'get',
        headers: { token: sessionStorage.getItem('token') }
    })
}

export function publishApp(appId) {
    return request({
        url: `/appInfo/publish/${appId}`,
        method: 'post',
        headers: { token: sessionStorage.getItem('token') }
    })
}

export function auditApp(appId, status) {
    return request({
        url: '/appInfo/audit',
        method: 'post',
        data: { appId, status },
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

// 修改：获取手游详情
export function getAppDetail(appId) {
    return request({
        url: `/appInfo/appWithVersion/${appId}`,
        method: 'get',
        headers: { 
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

export function getAdminList(pageNum, pageSize) {
    return request({
        url: '/user/admin/list',
        method: 'get',
        params: { pageNum, pageSize },
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

export function getDevList(pageNum, pageSize) {
    return request({
        url: '/user/dev/list',
        method: 'get',
        params: { pageNum, pageSize },
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

export function getNormalUserList(pageNum, pageSize) {
    return request({
        url: '/user/normal/list',
        method: 'get',
        params: { pageNum, pageSize },
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}

export function getAppInfoDetail(appId) {
    return request({
        url: `/appInfo/detail/${appId}`,
        method: 'get',
        headers: {
            token: sessionStorage.getItem('token'),
            userType: sessionStorage.getItem('userType')
        }
    })
}