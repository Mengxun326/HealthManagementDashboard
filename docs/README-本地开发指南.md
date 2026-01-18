# 医疗健康管理系统 - 本地开发部署指南

## 📋 项目简介
这是一个基于 React + TypeScript + Tailwind CSS 的医疗健康管理系统，包含用户端的完整功能模块。

## 🛠 技术栈
- **前端框架**: React 18.3.1
- **开发语言**: TypeScript
- **样式方案**: Tailwind CSS 4.1.12
- **图表库**: Recharts 2.15.2
- **UI 组件**: Radix UI + 自定义组件
- **图标库**: Lucide React
- **构建工具**: Vite 6.3.5

## 📦 导出步骤

### 方法一：从 Figma Make 下载（推荐）
1. 在 Figma Make 界面右上角，点击 **下载** 或 **Export** 按钮
2. 选择导出为 **源代码包**
3. 下载后解压到本地文件夹

### 方法二：手动复制文件
如果没有直接下载选项，请按照以下文件结构手动创建：

```
medical-health-system/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── patient/
│   │   │   │   ├── PatientHome.tsx
│   │   │   │   ├── PatientHealth.tsx
│   │   │   │   ├── PatientMessages.tsx
│   │   │   │   ├── PatientChronicDisease.tsx
│   │   │   │   ├── PatientProfile.tsx
│   │   │   │   └── PatientShop.tsx
│   │   │   ├── ui/
│   │   │   │   └── [所有 UI 组件]
│   │   │   ├── HealthChart.tsx
│   │   │   ├── PatientView.tsx
│   │   │   └── SingleMetricChart.tsx
│   │   └── App.tsx
│   └── styles/
│       ├── index.css
│       ├── tailwind.css
│       ├── theme.css
│       └── fonts.css
├── package.json
├── vite.config.ts
├── postcss.config.mjs
└── tsconfig.json
```

## 🚀 本地安装和运行

### 1. 前置要求
确保您的电脑已安装：
- **Node.js** (版本 >= 18.0.0) - [下载链接](https://nodejs.org/)
- **npm** 或 **pnpm** 或 **yarn** 包管理器

检查是否已安装：
```bash
node --version
npm --version
```

### 2. 安装依赖
在项目根目录打开终端，运行：

**使用 npm:**
```bash
npm install
```

**使用 pnpm (推荐，速度更快):**
```bash
# 如果没有安装 pnpm
npm install -g pnpm

# 安装依赖
pnpm install
```

**使用 yarn:**
```bash
yarn install
```

### 3. 启动开发服务器
```bash
# npm
npm run dev

# pnpm
pnpm dev

# yarn
yarn dev
```

启动成功后，在浏览器打开：
```
http://localhost:5173
```

### 4. 构建生产版本
```bash
# npm
npm run build

# pnpm
pnpm build

# yarn
yarn build
```

构建后的文件会生成在 `dist/` 目录。

## 📂 项目结构说明

```
src/
├── app/
│   ├── App.tsx                    # 主应用入口
│   └── components/
│       ├── patient/               # 用户端页面组件
│       │   ├── PatientHome.tsx           # 首页（健康画像+趋势图）
│       │   ├── PatientHealth.tsx         # 健康（AI报告解读、病历等）
│       │   ├── PatientMessages.tsx       # 消息（用药提醒+复诊提醒）
│       │   ├── PatientChronicDisease.tsx # 慢病管理（五高管理）
│       │   ├── PatientProfile.tsx        # 我的（健康管家+档案）
│       │   └── PatientShop.tsx           # 商店
│       ├── ui/                    # UI 基础组件库
│       ├── SingleMetricChart.tsx  # 单指标图表组件
│       └── PatientView.tsx        # 用户视图容器
└── styles/                        # 样式文件
    ├── index.css                  # 主样式入口
    ├── tailwind.css               # Tailwind 配置
    └── theme.css                  # 主题变量
```

## 🎯 核心功能模块

### 1️⃣ 首页 (PatientHome)
- **个人健康画像**: 正常/异常指标统计、健康红绿灯
- **健康趋势图**: 4个独立曲线图
  - 血压变化 (正常范围: 90-140 mmHg)
  - 血脂变化 (正常范围: 3.1-5.2 mmol/L)
  - 血糖变化 (正常范围: 3.9-6.1 mmol/L)
  - BMI变化 (正常范围: 18.5-24 kg/m²)
- **异常数据解读**: AI智能分析异常指标

### 2️⃣ 健康 (PatientHealth)
- **AI报告解读**: 智能分析体检报告，提供AI健康评分
- **我的病历**: 需人脸识别查看
- **线上问诊**: 在线咨询医生
- **医嘱**: 查看医生开具的医嘱和用药计划
- **健康课程**: 学习健康知识

### 3️⃣ 消息 (PatientMessages)
- **用药提醒**: 显示药物、剂量、服药时间（由医生设置）
- **复诊提醒**: 显示预约时间、医院科室（由医生设置）

### 4️⃣ 慢病管理 (PatientChronicDisease)
- **7个管理模块**:
  - 高血压
  - 高血糖
  - 高血脂
  - 高血压+高血糖
  - 高血糖+高血脂
  - 高血压+高血脂
  - 高血压+高血糖+高血脂
- 根据患者指标自动标红/标绿
- **AI健康趋势预测**: 预测未来健康走势
- **个性化干预方案**: 饮食建议、运动计划、用药提醒

### 5️⃣ 我的 (PatientProfile)
- **AI健康助手**: 24小时智能健康管理
- **档案管理**:
  - 历史报告
  - 过敏史
  - 过往病史
- **亲友账号**: 可查看亲友的所有健康信息

## 🔧 常见问题

### Q1: 安装依赖时报错？
**解决方案**:
```bash
# 清除缓存
npm cache clean --force

# 删除 node_modules 和 lock 文件
rm -rf node_modules package-lock.json

# 重新安装
npm install
```

### Q2: 启动时端口被占用？
**解决方案**:
修改 `vite.config.ts`，添加端口配置：
```typescript
export default defineConfig({
  server: {
    port: 3000, // 改为其他端口
  },
  // ... 其他配置
})
```

### Q3: 如何修改主题颜色？
**解决方案**:
编辑 `src/styles/theme.css` 文件中的 CSS 变量。

### Q4: 需要添加后端接口？
**建议步骤**:
1. 在 `src/services/` 目录下创建 API 服务文件
2. 使用 `fetch` 或 `axios` 进行接口调用
3. 将模拟数据替换为真实 API 数据

## 🌐 部署到生产环境

### 部署到 Vercel (推荐)
1. 访问 [vercel.com](https://vercel.com)
2. 导入 GitHub 仓库
3. 自动检测为 Vite 项目
4. 一键部署

### 部署到 Netlify
1. 访问 [netlify.com](https://netlify.com)
2. 拖拽 `dist` 文件夹到页面
3. 自动部署

### 部署到自有服务器
```bash
# 构建
npm run build

# 将 dist 目录上传到服务器
# 配置 Nginx 或 Apache 指向 dist 目录
```

Nginx 配置示例:
```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    root /path/to/dist;
    index index.html;
    
    location / {
        try_files $uri $uri/ /index.html;
    }
}
```

## 📱 下一步开发建议

### 集成后端 (Supabase)
- 用户认证 (登录/注册)
- 数据库持久化 (健康数据、报告等)
- 实时数据同步
- 文件存储 (报告图片上传)

### 功能增强
- [ ] 添加人脸识别功能
- [ ] 集成第三方支付 (商店模块)
- [ ] 实时聊天功能 (线上问诊)
- [ ] 推送通知 (用药/复诊提醒)
- [ ] 数据导出 (PDF报告)

### 性能优化
- [ ] 代码分割 (React.lazy + Suspense)
- [ ] 图片懒加载
- [ ] PWA 支持 (离线访问)
- [ ] CDN 加速

## 📞 技术支持

如有问题，请联系：
- 项目负责人: [您的联系方式]
- 技术文档: [文档链接]

---

**最后更新**: 2026年1月3日
**版本**: v1.0.0
