# 医疗健康管理系统

基于React和Spring Boot的完整医疗健康管理平台。包含前端React应用和后端Spring Boot API服务。

## ✨ 功能特性

- ✅ 用户认证和权限管理（JWT）
- ✅ 健康指标管理（血压、血糖、血脂、BMI）
- ✅ 体检报告上传和AI解读
- ✅ 慢病管理（高血压、高血糖、高血脂等）
- ✅ 用药提醒和复诊提醒
- ✅ 档案管理（过敏史、病史、亲友关系）
- ✅ 健康课程和医嘱管理
- ✅ 完整的API文档（Knife4j/Swagger）

## 🛠 技术栈

### 前端
- **框架**: React 18.3.1
- **语言**: TypeScript
- **样式**: Tailwind CSS 4.1.12
- **图表**: Recharts 2.15.2
- **UI组件**: Radix UI
- **构建工具**: Vite 6.3.5

### 后端
- **框架**: Spring Boot 2.7.18
- **ORM**: MyBatis Plus 3.5.3.1
- **数据库**: MySQL 8.0+
- **认证**: JWT
- **API文档**: Knife4j 4.3.0
- **连接池**: Druid 1.2.16

## 📁 项目结构

```
HealthManagementDashboard/
├── src/                    # 前端源代码（React + TypeScript）
│   ├── app/               # 应用主目录
│   │   ├── App.tsx        # 主应用组件
│   │   └── components/    # 组件目录
│   │       ├── patient/   # 患者相关组件
│   │       ├── ui/        # UI组件库
│   │       └── ...
│   ├── styles/            # 样式文件
│   └── main.tsx           # 入口文件
├── backend/               # 后端源代码（Spring Boot）
│   ├── src/main/java/    # Java源代码
│   ├── src/main/resources/# 配置文件
│   │   ├── application.yml.example  # 配置示例
│   │   └── db/schema.sql # 数据库脚本
│   ├── pom.xml            # Maven配置
│   └── README.md          # 后端说明
├── docs/                  # 项目文档
│   ├── README-本地开发指南.md
│   └── guidelines/        # 开发指南
├── package.json           # 前端依赖配置
├── vite.config.ts         # Vite配置
└── README.md              # 项目说明（本文件）
```

## 🚀 快速开始

### 前端开发

```bash
# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build
```

访问：http://localhost:5173

### 后端开发

```bash
cd backend

# 1. 创建数据库并执行SQL脚本
# 参考：backend/启动指南.md

# 2. 复制配置文件
cp src/main/resources/application.yml.example src/main/resources/application.yml
# 修改 application.yml 中的数据库配置

# 3. 运行项目
mvn spring-boot:run
```

访问：
- API文档：http://localhost:8080/doc.html
- Swagger UI：http://localhost:8080/swagger-ui/index.html

## 📚 文档

### 前端文档
- [本地开发指南](./docs/README-本地开发指南.md)
- [快速入门指南](./docs/QUICK_START.md)

### 后端文档
- [后端README](./backend/README.md)
- [启动指南](./backend/启动指南.md)
- [API测试指南](./backend/API测试指南.md)
- [API文档](./backend/API_DOCUMENTATION.md)

## 🔧 配置说明

### 后端配置

1. 复制配置文件：
   ```bash
   cd backend
   cp src/main/resources/application.yml.example src/main/resources/application.yml
   ```

2. 修改 `application.yml`：
   - 数据库用户名和密码
   - JWT密钥（生产环境必须修改）
   - 文件上传路径

3. 创建数据库：
   ```sql
   CREATE DATABASE health_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

4. 执行SQL脚本：
   ```bash
   mysql -u root -p health_management < backend/src/main/resources/db/schema.sql
   ```

## 🧪 测试

### API测试

1. 启动后端服务
2. 访问API文档：http://localhost:8080/doc.html
3. 或使用Postman导入Collection：`backend/HealthManagementAPI.postman_collection.json`

详细测试步骤请查看：[API测试指南](./backend/API测试指南.md)

### 测试账号

- 用户名：`admin`
- 密码：`123456`

## 📦 部署

### 前端部署

```bash
npm run build
# 将 dist 目录部署到静态服务器（Nginx、Apache等）
```

### 后端部署

```bash
cd backend
mvn clean package
java -jar target/health-management-backend-1.0.0.jar
```

## 🤝 贡献

欢迎提交Issue和Pull Request！

## 📄 许可证

MIT License

## 🔗 相关链接

- 原始设计：[Figma](https://www.figma.com/design/hvZjzUWc5EviF7WB3a9hg3/Health-Management-Dashboard)
- Spring Boot文档：https://spring.io/projects/spring-boot
- React文档：https://react.dev

---

**注意：** 上传到GitHub前，请确保已处理敏感信息（数据库密码、JWT密钥等）。参考 [GitHub上传指南](./GitHub上传指南.md)
