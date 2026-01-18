# 快速开始指南

## 环境要求

- JDK 1.8 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.0 或更高版本

## 安装步骤

### 1. 配置数据库

创建数据库并执行SQL脚本：

```bash
# 登录MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE health_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 执行SQL脚本
source src/main/resources/db/schema.sql
```

或者直接执行：

```bash
mysql -u root -p health_management < src/main/resources/db/schema.sql
```

### 2. 修改配置文件

编辑 `src/main/resources/application.yml`，修改数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/health_management?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: root  # 修改为你的数据库用户名
    password: root  # 修改为你的数据库密码
```

### 3. 创建上传目录

在项目根目录创建上传文件夹：

```bash
# Windows
mkdir upload

# Linux/Mac
mkdir -p upload
```

### 4. 编译项目

```bash
mvn clean compile
```

### 5. 运行项目

```bash
mvn spring-boot:run
```

或者打包后运行：

```bash
mvn clean package
java -jar target/health-management-backend-1.0.0.jar
```

## 访问应用

- **应用地址**: http://localhost:8080
- **API文档**: http://localhost:8080/doc.html
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

## 测试登录

使用以下账号登录（默认测试账号）：

```json
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "123456"
}
```

响应示例：

```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "userId": "1",
    "username": "admin",
    "expiration": 86400000
  }
}
```

## 使用API

登录后，使用返回的token访问其他API接口：

```
Authorization: Bearer {token}
```

## 常见问题

### 1. 端口被占用

修改 `application.yml` 中的端口：

```yaml
server:
  port: 8081  # 改为其他端口
```

### 2. 数据库连接失败

检查：
- MySQL服务是否启动
- 数据库用户名密码是否正确
- 数据库是否已创建
- 防火墙是否允许连接

### 3. 文件上传失败

确保：
- `upload` 目录已创建
- 目录有写入权限
- 文件大小不超过10MB

### 4. 编译错误

确保：
- JDK版本正确（1.8+）
- Maven配置正确
- 网络连接正常（下载依赖）

## 开发建议

1. 使用IDE（如IntelliJ IDEA或Eclipse）导入Maven项目
2. 配置IDE的代码格式化规则
3. 使用Postman或Apifox测试API接口
4. 查看日志文件：`logs/health-management.log`

## 下一步

- 查看 [API文档](./API_DOCUMENTATION.md) 了解所有接口
- 查看 [README.md](./README.md) 了解项目结构
- 集成前端React应用
- 配置生产环境
