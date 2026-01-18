# 医疗健康管理系统 - 后端服务

基于JeeecgBoot框架构建的医疗健康管理系统后端API服务。

## 技术栈

- Spring Boot 2.7.18
- MyBatis Plus 3.5.3.1
- MySQL 8.0+
- JWT认证
- Knife4j API文档
- Druid连接池

## 项目结构

```
backend/
├── src/main/java/org/jeecg/modules/health/
│   ├── controller/          # 控制器层
│   ├── service/            # 服务层
│   ├── mapper/             # 数据访问层
│   ├── entity/             # 实体类
│   ├── dto/                # 数据传输对象
│   ├── vo/                 # 视图对象
│   └── config/             # 配置类
├── src/main/resources/
│   ├── mapper/             # MyBatis XML映射文件
│   ├── db/                 # 数据库脚本
│   └── application.yml     # 应用配置
└── pom.xml                 # Maven依赖配置
```

## 快速开始

详细步骤请查看 [QUICK_START.md](./QUICK_START.md)

### 简要步骤

1. **环境要求**: JDK 1.8+, Maven 3.6+, MySQL 8.0+
2. **创建数据库**: 执行 `src/main/resources/db/schema.sql`
3. **配置数据库**: 修改 `application.yml` 中的数据库连接信息
4. **创建上传目录**: 在项目根目录创建 `upload` 文件夹
5. **运行项目**: `mvn spring-boot:run`
6. **访问API文档**: http://localhost:8080/doc.html

### 默认测试账号

- 用户名: `admin`
- 密码: `123456`

## API接口

完整的API文档请查看 [API_DOCUMENTATION.md](./API_DOCUMENTATION.md)

### 主要功能模块

- **认证相关**: 登录、登出、用户信息
- **患者管理**: 患者信息、健康画像摘要
- **健康指标**: 指标记录、趋势分析、异常检测
- **体检报告**: 报告上传、AI解读
- **慢病管理**: 慢病记录、干预方案、趋势预测
- **消息提醒**: 用药提醒、复诊提醒
- **档案管理**: 过敏史、过往病史、亲友管理
- **其他功能**: 医嘱、健康课程、文件上传

## 开发说明

### 代码生成

使用Jeeecg代码生成器可以快速生成CRUD代码：
1. 在数据库中创建表结构
2. 使用代码生成器选择表
3. 生成Entity、Mapper、Service、Controller等代码

### 注意事项

1. 所有API接口需要JWT认证（除登录接口外）
2. 请求头需要携带：`Authorization: Bearer {token}`
3. 患者只能访问自己的数据
4. 文件上传大小限制：10MB

## 许可证

MIT License
