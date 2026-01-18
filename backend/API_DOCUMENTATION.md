# 医疗健康管理系统 - API接口文档

## 基础信息

- **Base URL**: `http://localhost:8080`
- **API前缀**: `/api`
- **认证方式**: JWT Token
- **Token格式**: `Bearer {token}`

## 认证相关

### 用户登录

**POST** `/api/auth/login`

**请求体**:
```json
{
  "username": "admin",
  "password": "123456"
}
```

**响应**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "userId": "1",
    "username": "admin",
    "expiration": 86400000
  },
  "timestamp": 1234567890
}
```

### 用户登出

**POST** `/api/auth/logout`

**请求头**: `Authorization: Bearer {token}`

**响应**:
```json
{
  "code": 200,
  "message": "登出成功",
  "data": null
}
```

### 获取当前用户信息

**GET** `/api/auth/userinfo`

**请求头**: `Authorization: Bearer {token}`

## 患者管理

### 获取患者信息

**GET** `/api/patient/info`

**请求头**: `Authorization: Bearer {token}`

### 更新患者信息

**PUT** `/api/patient/info`

**请求头**: `Authorization: Bearer {token}`

**请求体**:
```json
{
  "name": "张三",
  "gender": 1,
  "birthDate": "1990-01-01",
  "phone": "13800138000"
}
```

### 获取健康画像摘要

**GET** `/api/patient/health-summary`

**请求头**: `Authorization: Bearer {token}`

## 健康指标管理

### 获取健康指标列表

**GET** `/api/health/metric/list`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）
- `metricType`: 指标类型（可选）：`BLOOD_PRESSURE`、`BLOOD_SUGAR`、`CHOLESTEROL`、`BMI`

**请求头**: `Authorization: Bearer {token}`

### 添加健康指标记录

**POST** `/api/health/metric/add`

**请求头**: `Authorization: Bearer {token}`

**请求体**:
```json
{
  "metricType": "BLOOD_PRESSURE",
  "metricValue": 135.00,
  "recordDate": "2024-01-20",
  "normalRangeMin": 90.00,
  "normalRangeMax": 140.00
}
```

### 获取健康指标趋势数据

**GET** `/api/health/metric/trend`

**请求参数**:
- `metricType`: 指标类型（必填）
- `startDate`: 开始日期（可选，格式：yyyy-MM-dd）
- `endDate`: 结束日期（可选，格式：yyyy-MM-dd）

**请求头**: `Authorization: Bearer {token}`

### 获取异常指标列表

**GET** `/api/health/metric/abnormal`

**请求头**: `Authorization: Bearer {token}`

## 体检报告管理

### 获取报告列表

**GET** `/api/health/report/list`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）

**请求头**: `Authorization: Bearer {token}`

### 获取报告详情

**GET** `/api/health/report/{id}`

**请求头**: `Authorization: Bearer {token}`

### 上传体检报告

**POST** `/api/health/report/upload`

**请求头**: `Authorization: Bearer {token}`

**请求参数**（multipart/form-data）:
- `file`: 文件（必填）
- `reportType`: 报告类型（必填）
- `reportDate`: 报告日期（必填，格式：yyyy-MM-dd）
- `hospitalName`: 医院名称（可选）

### AI报告解读

**POST** `/api/health/report/ai-analysis`

**请求参数**:
- `reportId`: 报告ID（必填）

**请求头**: `Authorization: Bearer {token}`

## 慢病管理

### 获取慢病列表

**GET** `/api/chronic-disease/list`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）

**请求头**: `Authorization: Bearer {token}`

### 获取慢病详情

**GET** `/api/chronic-disease/{id}`

**请求头**: `Authorization: Bearer {token}`

### 获取干预方案

**GET** `/api/chronic-disease/intervention-plan`

**请求参数**:
- `diseaseId`: 慢病ID（必填）

**请求头**: `Authorization: Bearer {token}`

**响应**:
```json
{
  "code": 200,
  "data": {
    "dietAdvice": ["减少钠盐摄入", "多食用富含钾的食物"],
    "exercisePlan": ["每周5次有氧运动", "推荐快走、游泳"],
    "medicationReminder": ["按时服药", "定期监测"]
  }
}
```

### AI健康趋势预测

**GET** `/api/chronic-disease/trend-prediction`

**请求参数**:
- `diseaseId`: 慢病ID（必填）

**请求头**: `Authorization: Bearer {token}`

## 消息提醒

### 获取用药提醒列表

**GET** `/api/reminder/medication`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）

**请求头**: `Authorization: Bearer {token}`

### 获取复诊提醒列表

**GET** `/api/reminder/appointment`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）

**请求头**: `Authorization: Bearer {token}`

### 标记提醒为已读

**PUT** `/api/reminder/{id}/read`

**请求参数**:
- `type`: 提醒类型（必填）：`medication` 或 `appointment`

**请求头**: `Authorization: Bearer {token}`

## 档案管理

### 获取过敏史

**GET** `/api/profile/allergy-history`

**请求头**: `Authorization: Bearer {token}`

### 添加过敏史

**POST** `/api/profile/allergy-history`

**请求头**: `Authorization: Bearer {token}`

**请求体**:
```json
{
  "allergen": "青霉素",
  "reaction": "皮疹、呼吸困难",
  "severity": "SEVERE",
  "recordedDate": "2020-03-15"
}
```

### 获取过往病史

**GET** `/api/profile/medical-history`

**请求头**: `Authorization: Bearer {token}`

### 获取亲友列表

**GET** `/api/profile/family-members`

**请求头**: `Authorization: Bearer {token}`

### 添加亲友

**POST** `/api/profile/family-members`

**请求头**: `Authorization: Bearer {token}`

**请求体**:
```json
{
  "memberName": "张三",
  "relation": "SPOUSE",
  "memberPhone": "13800138000",
  "permissionLevel": "VIEW"
}
```

## 其他功能

### 获取医嘱列表

**GET** `/api/doctor/advice`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）

**请求头**: `Authorization: Bearer {token}`

### 获取健康课程列表

**GET** `/api/health/course/list`

**请求参数**:
- `current`: 当前页（默认：1）
- `size`: 每页数量（默认：10）
- `courseType`: 课程类型（可选）

**请求头**: `Authorization: Bearer {token}`

## 文件上传

### 上传文件

**POST** `/api/file/upload`

**请求头**: `Authorization: Bearer {token}`

**请求参数**（multipart/form-data）:
- `file`: 文件（必填）
- `subDir`: 子目录（可选，默认：common）

**响应**:
```json
{
  "code": 200,
  "message": "上传成功",
  "data": {
    "url": "/upload/reports/2024/01/20/uuid.pdf",
    "filename": "report.pdf",
    "size": "1024000"
  }
}
```

### 删除文件

**DELETE** `/api/file/delete`

**请求参数**:
- `fileUrl`: 文件URL（必填）

**请求头**: `Authorization: Bearer {token}`

## 错误码说明

- `200`: 成功
- `401`: 未授权（Token无效或过期）
- `500`: 服务器错误

## 注意事项

1. 所有需要认证的接口都需要在请求头中携带 `Authorization: Bearer {token}`
2. Token有效期为24小时
3. 文件上传大小限制：10MB
4. 支持的文件类型：jpg, jpeg, png, gif, pdf, doc, docx
5. 日期格式统一使用：`yyyy-MM-dd` 或 `yyyy-MM-dd HH:mm:ss`
