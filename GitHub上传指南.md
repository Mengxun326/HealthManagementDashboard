# GitHub上传指南

## 📋 上传前准备

### 1. 检查敏感信息

**重要：** 上传前请确保以下敏感信息不会被提交：

- ✅ 数据库密码（application.yml中的password）
- ✅ JWT密钥（application.yml中的jwt.secret）
- ✅ API密钥（如果有）
- ✅ 个人配置信息

### 2. 处理敏感配置文件

**方案一：使用示例文件（推荐）**

1. 将 `backend/src/main/resources/application.yml` 中的敏感信息替换为占位符
2. 创建 `application.yml.example` 作为模板
3. 确保 `.gitignore` 已配置忽略包含敏感信息的文件

**方案二：使用环境变量**

在 `application.yml` 中使用环境变量：
```yaml
spring:
  datasource:
    password: ${DB_PASSWORD:your_password}
```

---

## 🚀 上传步骤

### 步骤1：初始化Git仓库

```bash
cd e:\Code\HealthManagementDashboard

# 初始化Git仓库
git init

# 检查.gitignore是否生效
git status
```

### 步骤2：检查要提交的文件

```bash
# 查看将要提交的文件（确保没有敏感信息）
git status

# 查看具体文件内容（检查application.yml等配置文件）
git diff
```

### 步骤3：添加文件到暂存区

```bash
# 添加所有文件（.gitignore会自动排除不需要的文件）
git add .

# 或者分步添加
git add README.md
git add .gitignore
git add src/
git add backend/
git add docs/
git add package.json
git add vite.config.ts
# ... 其他文件
```

### 步骤4：提交代码

```bash
# 提交代码
git commit -m "Initial commit: 医疗健康管理系统 - 前后端完整代码"

# 或者更详细的提交信息
git commit -m "feat: 医疗健康管理系统初始版本

- 前端：React + TypeScript + Tailwind CSS
- 后端：Spring Boot + MyBatis Plus + MySQL
- 功能：健康指标管理、体检报告、慢病管理、消息提醒等
- 文档：完整的API文档和测试指南"
```

### 步骤5：在GitHub创建仓库

1. 登录GitHub
2. 点击右上角 `+` → `New repository`
3. 填写仓库信息：
   - **Repository name**: `HealthManagementDashboard`（或你喜欢的名字）
   - **Description**: `医疗健康管理系统 - 基于React和Spring Boot的完整健康管理平台`
   - **Visibility**: `Public` 或 `Private`（根据你的需求）
   - **不要**勾选 `Initialize this repository with a README`（因为本地已有）
4. 点击 `Create repository`

### 步骤6：连接远程仓库并推送

```bash
# 添加远程仓库（替换YOUR_USERNAME为你的GitHub用户名）
git remote add origin https://github.com/YOUR_USERNAME/HealthManagementDashboard.git

# 或者使用SSH（如果配置了SSH密钥）
# git remote add origin git@github.com:YOUR_USERNAME/HealthManagementDashboard.git

# 查看远程仓库
git remote -v

# 推送代码到GitHub
git branch -M main
git push -u origin main
```

---

## ⚠️ 重要：处理敏感信息

### 1. 修改application.yml

在推送前，请修改 `backend/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    username: root
    password: your_password_here  # ⚠️ 改为占位符或删除
```

**推荐做法：**

1. **创建示例文件**：
   ```bash
   # 复制配置文件为示例文件
   cp backend/src/main/resources/application.yml backend/src/main/resources/application.yml.example
   ```

2. **修改application.yml**，将敏感信息替换为占位符：
   ```yaml
   spring:
     datasource:
       username: ${DB_USERNAME:root}
       password: ${DB_PASSWORD:your_password}
   
   jwt:
     secret: ${JWT_SECRET:your-secret-key-here}
   ```

3. **确保.gitignore包含**：
   ```
   application-local.yml
   application-dev.yml
   application-prod.yml
   ```

### 2. 创建配置说明文件

创建 `backend/src/main/resources/application.yml.example`：

```yaml
# 配置文件示例
# 复制此文件为 application.yml 并修改相应配置

server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/health_management?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: root
    password: your_password_here  # ⚠️ 修改为你的数据库密码

jwt:
  secret: your-jwt-secret-key-here  # ⚠️ 修改为强密码（建议32位以上随机字符串）

file:
  upload:
    path: ./upload  # 修改为你的上传目录路径
```

---

## 📝 更新README文件

更新根目录的 `README.md`，包含前后端完整信息：

```markdown
# 医疗健康管理系统

基于React和Spring Boot的完整医疗健康管理平台。

## 项目结构

```
HealthManagementDashboard/
├── src/                    # 前端源代码（React + TypeScript）
├── backend/                # 后端源代码（Spring Boot）
├── docs/                   # 项目文档
└── README.md               # 项目说明
```

## 技术栈

### 前端
- React 18.3.1
- TypeScript
- Tailwind CSS 4.1.12
- Vite 6.3.5
- Recharts（图表库）

### 后端
- Spring Boot 2.7.18
- MyBatis Plus 3.5.3.1
- MySQL 8.0+
- JWT认证
- Knife4j API文档

## 快速开始

### 前端

```bash
cd HealthManagementDashboard
npm install
npm run dev
```

### 后端

```bash
cd backend
# 1. 创建数据库并执行SQL脚本
# 2. 修改 application.yml 中的数据库配置
# 3. 运行项目
mvn spring-boot:run
```

详细文档请查看：
- [前端开发指南](./docs/README-本地开发指南.md)
- [后端启动指南](./backend/启动指南.md)
- [API测试指南](./backend/API测试指南.md)

## 功能特性

- ✅ 用户认证和权限管理
- ✅ 健康指标管理（血压、血糖、血脂、BMI）
- ✅ 体检报告上传和AI解读
- ✅ 慢病管理（高血压、高血糖、高血脂等）
- ✅ 用药提醒和复诊提醒
- ✅ 档案管理（过敏史、病史、亲友）
- ✅ 健康课程和医嘱管理

## API文档

启动后端服务后，访问：
- Knife4j文档：http://localhost:8080/doc.html
- Swagger UI：http://localhost:8080/swagger-ui/index.html

## 许可证

MIT License
```

---

## 🔒 安全检查清单

上传前请确认：

- [ ] `.gitignore` 已正确配置
- [ ] `application.yml` 中的密码已替换为占位符
- [ ] JWT密钥已替换为占位符
- [ ] 没有硬编码的API密钥
- [ ] 没有个人敏感信息
- [ ] 已创建 `application.yml.example` 示例文件
- [ ] README文件已更新

---

## 📦 完整的Git命令流程

```bash
# 1. 进入项目目录
cd e:\Code\HealthManagementDashboard

# 2. 初始化Git仓库
git init

# 3. 检查.gitignore
git status

# 4. 添加所有文件
git add .

# 5. 提交代码
git commit -m "feat: 医疗健康管理系统初始版本

- 前端：React + TypeScript + Tailwind CSS
- 后端：Spring Boot + MyBatis Plus + MySQL
- 完整功能模块和API文档"

# 6. 添加远程仓库（替换YOUR_USERNAME）
git remote add origin https://github.com/YOUR_USERNAME/HealthManagementDashboard.git

# 7. 设置主分支
git branch -M main

# 8. 推送代码
git push -u origin main
```

---

## 🎯 后续操作

### 添加更多提交

```bash
# 修改文件后
git add .
git commit -m "feat: 添加新功能"
git push
```

### 创建分支

```bash
# 创建并切换到新分支
git checkout -b feature/new-feature

# 提交更改
git add .
git commit -m "feat: 新功能"

# 推送到远程
git push -u origin feature/new-feature
```

### 添加标签（版本发布）

```bash
# 创建标签
git tag -a v1.0.0 -m "版本1.0.0：初始发布"

# 推送标签
git push origin v1.0.0
```

---

## 📚 GitHub仓库设置建议

### 1. 添加仓库描述和标签

- Description: `医疗健康管理系统 - 基于React和Spring Boot的完整健康管理平台`
- Topics: `react`, `spring-boot`, `health-management`, `typescript`, `mysql`, `rest-api`

### 2. 添加README徽章

可以在README.md中添加：

```markdown
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)
![React](https://img.shields.io/badge/React-18.3.1-blue.svg)
```

### 3. 添加GitHub Actions（可选）

创建 `.github/workflows/ci.yml` 用于CI/CD：

```yaml
name: CI

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '8'
      - name: Build backend
        run: |
          cd backend
          mvn clean compile
```

---

## ⚠️ 常见问题

### 问题1：推送时要求输入用户名密码

**解决方法：**
1. 使用Personal Access Token（推荐）
   - GitHub Settings → Developer settings → Personal access tokens
   - 生成新token，权限选择 `repo`
   - 使用token作为密码

2. 或配置SSH密钥
   ```bash
   # 生成SSH密钥
   ssh-keygen -t ed25519 -C "your_email@example.com"
   
   # 添加到GitHub
   # 然后使用SSH URL
   git remote set-url origin git@github.com:YOUR_USERNAME/HealthManagementDashboard.git
   ```

### 问题2：文件太大无法推送

**解决方法：**
```bash
# 检查大文件
git ls-files | xargs ls -la | sort -k5 -rn | head -10

# 如果node_modules被提交了，删除并重新提交
git rm -r --cached node_modules
git commit -m "chore: 移除node_modules"
```

### 问题3：敏感信息已提交

**解决方法：**
```bash
# 1. 修改敏感信息
# 2. 从Git历史中移除（如果已推送）
git filter-branch --force --index-filter \
  "git rm --cached --ignore-unmatch backend/src/main/resources/application.yml" \
  --prune-empty --tag-name-filter cat -- --all

# 3. 强制推送（谨慎使用）
git push origin --force --all
```

---

## 📞 需要帮助？

- GitHub文档：https://docs.github.com/
- Git文档：https://git-scm.com/doc
- 项目文档：[README.md](./README.md)

---

**祝上传顺利！记得检查敏感信息！**
