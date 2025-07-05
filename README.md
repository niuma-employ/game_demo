好的，以下是适合直接保存为 Markdown 文件的后端开发文档内容。你可以将其复制到 `README.md` 或其他 `.md` 文件中，或用 Markdown 编辑器直接查看。

---

# 后端开发文档

## 一、项目简介

本项目为游戏应用管理平台后端，基于 Spring Boot 框架开发，集成 MyBatis、Redis、JWT 等技术，实现了用户管理、应用信息管理、版本管理、收藏、缓存等功能。

---

## 二、主要技术栈

- Spring Boot 2.3.12.RELEASE
- MyBatis
- MySQL
- Redis
- JWT（JSON Web Token）
- Maven

---

## 三、项目结构

```
src/
 └── main/
     ├── java/com/lyh/demo_game/
     │    ├── controller/   // 控制器层，处理HTTP请求
     │    ├── service/      // 业务逻辑层
     │    ├── mapper/       // MyBatis数据访问层
     │    ├── domain/       // 实体类
     │    ├── utils/        // 工具类（如JwtUtils、RedisUtils）
     │    ├── config/       // 配置类（如RedisConfig）
     │    └── intercept/    // 拦截器
     └── resources/
          ├── application.yml // 配置文件
          └── mapper/         // MyBatis映射文件
```

---

## 四、环境配置

### 1. JDK
- 推荐 JDK 1.8（已测试 Amazon Corretto 1.8）

### 2. 数据库
- MySQL 5.7+，导入 `beyond_platform_db.sql`

### 3. Redis
- 推荐 Redis 5.0+，本地默认端口 6379

### 4. 配置文件
`src/main/resources/application.yml` 示例：

```yaml
server:
  port: 9090
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    username: root
    password: your_password
  redis:
    host: localhost
    port: 6379
    database: 0
    timeout: 10000
    lettuce:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 0
upload:
  path: c:/Users/Lang/Desktop/
```

---

## 五、核心功能说明

### 1. 用户管理
- 普通用户注册、登录
- 管理员、开发者登录
- 用户分页查询

### 2. 应用信息管理
- 应用的增删改查
- 应用版本管理
- 应用 logo 上传
- 应用审核、上架、下架

### 3. 收藏功能
- 用户可收藏/取消收藏应用
- 查询我的收藏

### 4. 缓存功能（Redis）
- 应用详情、应用带版本详情缓存
- 缓存自动失效（1小时）
- 数据变更时自动清理相关缓存

---

## 六、接口说明（部分示例）

### 1. 用户相关

- **注册**
  - `POST /normalUser/register`
  - 请求体：`{ "userCode": "xxx", "userPassword": "xxx" }`
  - 返回：`success` 或 `用户已存在`

- **登录**
  - `POST /login`
  - 请求体：`{ "username": "xxx", "password": "xxx", "userType": "normal|dev|admin" }`
  - 返回：`token`

### 2. 应用相关

- **分页查询**
  - `POST /appInfo/page?pageNum=1`
  - Header: `token`, `userType`
  - 请求体：`{}`

- **应用详情**
  - `GET /appInfo/detail/{appId}`

- **测试缓存**
  - `GET /appInfo/testCache/{appId}`

- **更新缓存**
  - `POST /appInfo/testCacheUpdate/{appId}`

### 3. 收藏相关

- **收藏**
  - `POST /appInfo/collect/{appId}`

- **取消收藏**
  - `POST /appInfo/uncollect/{appId}`

---

## 七、缓存说明

- **缓存实现**：使用 Redis 存储应用详情、应用带版本详情，key 规则如 `game:detail:{appId}`、`game:detail:{appId}:with_version`。
- **缓存时机**：查询详情时先查缓存，未命中则查数据库并写入缓存。
- **缓存失效**：应用信息变更（如编辑、删除）时自动清理相关缓存。
- **缓存过期**：1小时自动过期。

---

## 八、认证与安全

- **JWT**：所有需要登录的接口需在 Header 传递 `token`，后端通过拦截器统一校验。
- **拦截器**：`LoginIntercept` 拦截所有请求，部分接口（如登录、注册、静态资源）放行。

---

## 九、常见问题

1. **端口被占用**  
   解决：结束占用进程或修改 `application.yml` 的 `server.port`。

2. **Redis 连接失败**  
   解决：确保 Redis 服务已启动，配置正确。

3. **token 校验失败**  
   解决：确认 token 是否过期、格式正确，或重新登录获取新 token。

---

## 十、开发&调试建议

- 推荐使用 Postman/Apifox 进行接口调试。
- 代码有详细注释，便于二次开发和维护。
- 如需扩展缓存、接口、权限等功能，建议遵循现有分层结构。

---

如需更详细的接口文档（如参数、返回值、示例），可补充 Swagger 或手动维护接口文档表格。
