# API接口测试指南

## 📋 测试准备

### 1. 测试工具选择

推荐使用以下工具之一：
- **Postman**（推荐）：图形化界面，易于使用
- **Apifox**：国产工具，功能强大
- **curl**：命令行工具，适合快速测试
- **Knife4j文档**：浏览器直接测试（http://localhost:8080/doc.html）

### 2. 环境准备

- ✅ 后端服务已启动（http://localhost:8080）
- ✅ 数据库已创建并执行SQL脚本
- ✅ 已准备好测试数据（可选）

### 3. 重要说明

- 除登录接口外，**所有接口都需要JWT Token认证**
- Token格式：`Authorization: Bearer {token}`
- Token有效期：24小时
- 测试账号：`admin` / `123456`

---

## 🔐 第一步：获取Token（必须）

### 接口：用户登录

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/auth/login`
- **Headers**: 
  ```
  Content-Type: application/json
  ```
- **Body** (JSON):
  ```json
  {
    "username": "admin",
    "password": "123456"
  }
  ```

**使用Postman测试：**
1. 创建新请求
2. 方法选择 `POST`
3. URL输入：`http://localhost:8080/api/auth/login`
4. Headers标签页，添加：
   - Key: `Content-Type`
   - Value: `application/json`
5. Body标签页，选择 `raw` 和 `JSON`，输入：
   ```json
   {
     "username": "admin",
     "password": "123456"
   }
   ```
6. 点击 Send
7. **重要**：复制响应中的 `token` 值，后续所有请求都需要使用

**使用curl测试：**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"admin\",\"password\":\"123456\"}"
```

**预期响应：**
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsImlhdCI6MTY3NDU2Nzg5MCwiZXhwIjoxNjc0NjU0MjkwfQ...",
    "userId": "1",
    "username": "admin",
    "expiration": 86400000
  },
  "timestamp": 1674567890123
}
```

**保存Token：**
- 复制 `data.token` 的值
- 在Postman中，可以创建环境变量 `token`，后续请求中使用 `{{token}}`

---

## 📝 测试接口清单

### 一、认证相关接口

#### 1.1 用户登录 ✅（已完成，见上方）

#### 1.2 用户登出

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/auth/logout`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**Postman步骤：**
1. 方法：`POST`
2. URL：`http://localhost:8080/api/auth/logout`
3. Headers：
   - Key: `Authorization`
   - Value: `Bearer {你的token}`（替换为实际token）

**curl命令：**
```bash
curl -X POST http://localhost:8080/api/auth/logout \
  -H "Authorization: Bearer {你的token}"
```

**预期响应：**
```json
{
  "code": 200,
  "message": "登出成功",
  "data": null,
  "timestamp": 1674567890123
}
```

#### 1.3 获取当前用户信息

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/auth/userinfo`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/auth/userinfo`
3. Headers：添加 `Authorization: Bearer {你的token}`

**curl命令：**
```bash
curl -X GET http://localhost:8080/api/auth/userinfo \
  -H "Authorization: Bearer {你的token}"
```

---

### 二、患者管理接口

#### 2.1 获取患者信息

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/patient/info`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/patient/info`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": "...",
    "userId": "1",
    "name": "张三",
    "gender": 1,
    ...
  }
}
```

#### 2.2 更新患者信息

**请求信息：**
- **方法**: `PUT`
- **URL**: `http://localhost:8080/api/patient/info`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  Content-Type: application/json
  ```
- **Body** (JSON):
  ```json
  {
    "name": "张三",
    "gender": 1,
    "birthDate": "1990-01-01",
    "phone": "13800138000",
    "idCard": "110101199001011234",
    "address": "北京市朝阳区xxx"
  }
  ```

**Postman步骤：**
1. 方法：`PUT`
2. URL：`http://localhost:8080/api/patient/info`
3. Headers：
   - `Authorization: Bearer {你的token}`
   - `Content-Type: application/json`
4. Body（raw JSON）：
   ```json
   {
     "name": "张三",
     "gender": 1,
     "birthDate": "1990-01-01",
     "phone": "13800138000"
   }
   ```

#### 2.3 获取健康画像摘要

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/patient/health-summary`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "normalCount": 8,
    "abnormalCount": 4,
    "totalCount": 12,
    "status": "需要注意"
  }
}
```

---

### 三、健康指标管理接口

#### 3.1 获取健康指标列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/health/metric/list`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）
  - `metricType`: 指标类型（可选）：`BLOOD_PRESSURE`、`BLOOD_SUGAR`、`CHOLESTEROL`、`BMI`

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/health/metric/list?current=1&size=10`
3. 或者添加参数：
   - Params标签页
   - Key: `current`, Value: `1`
   - Key: `size`, Value: `10`
   - Key: `metricType`, Value: `BLOOD_PRESSURE`（可选）
4. Headers：`Authorization: Bearer {你的token}`

**curl命令：**
```bash
curl -X GET "http://localhost:8080/api/health/metric/list?current=1&size=10" \
  -H "Authorization: Bearer {你的token}"
```

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": "...",
        "patientId": "1",
        "metricType": "BLOOD_PRESSURE",
        "metricValue": 135.00,
        "recordDate": "2024-01-20",
        "status": "NORMAL"
      }
    ],
    "total": 10,
    "size": 10,
    "current": 1,
    "pages": 1
  }
}
```

#### 3.2 添加健康指标记录

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/health/metric/add`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  Content-Type: application/json
  ```
- **Body** (JSON):
  ```json
  {
    "metricType": "BLOOD_PRESSURE",
    "metricValue": 135.00,
    "recordDate": "2024-01-20",
    "normalRangeMin": 90.00,
    "normalRangeMax": 140.00,
    "remark": "早晨测量"
  }
  ```

**Postman步骤：**
1. 方法：`POST`
2. URL：`http://localhost:8080/api/health/metric/add`
3. Headers：
   - `Authorization: Bearer {你的token}`
   - `Content-Type: application/json`
4. Body（raw JSON）：
   ```json
   {
     "metricType": "BLOOD_PRESSURE",
     "metricValue": 135.00,
     "recordDate": "2024-01-20",
     "normalRangeMin": 90.00,
     "normalRangeMax": 140.00
   }
   ```

**测试数据示例：**

**血压数据：**
```json
{
  "metricType": "BLOOD_PRESSURE",
  "metricValue": 135.00,
  "recordDate": "2024-01-20",
  "normalRangeMin": 90.00,
  "normalRangeMax": 140.00
}
```

**血糖数据：**
```json
{
  "metricType": "BLOOD_SUGAR",
  "metricValue": 6.2,
  "recordDate": "2024-01-20",
  "normalRangeMin": 3.9,
  "normalRangeMax": 6.1
}
```

**血脂数据：**
```json
{
  "metricType": "CHOLESTEROL",
  "metricValue": 6.2,
  "recordDate": "2024-01-20",
  "normalRangeMin": 3.1,
  "normalRangeMax": 5.7
}
```

**BMI数据：**
```json
{
  "metricType": "BMI",
  "metricValue": 26.5,
  "recordDate": "2024-01-20",
  "normalRangeMin": 18.5,
  "normalRangeMax": 24.0
}
```

#### 3.3 获取健康指标趋势数据

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/health/metric/trend`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `metricType`: 指标类型（必填）：`BLOOD_PRESSURE`、`BLOOD_SUGAR`、`CHOLESTEROL`、`BMI`
  - `startDate`: 开始日期（可选，格式：yyyy-MM-dd）
  - `endDate`: 结束日期（可选，格式：yyyy-MM-dd）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/health/metric/trend?metricType=BLOOD_PRESSURE&startDate=2024-01-01&endDate=2024-01-31`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": "...",
      "metricType": "BLOOD_PRESSURE",
      "metricValue": 135.00,
      "recordDate": "2024-01-20",
      "status": "NORMAL"
    }
  ]
}
```

#### 3.4 获取异常指标列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/health/metric/abnormal`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": "...",
      "metricType": "CHOLESTEROL",
      "metricValue": 6.2,
      "recordDate": "2024-01-20",
      "status": "ABNORMAL",
      "normalRangeMin": 3.1,
      "normalRangeMax": 5.7
    }
  ]
}
```

---

### 四、体检报告管理接口

#### 4.1 获取报告列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/health/report/list`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/health/report/list?current=1&size=10`
3. Headers：`Authorization: Bearer {你的token}`

#### 4.2 获取报告详情

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/health/report/{id}`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/health/report/123456`（替换为实际报告ID）
3. Headers：`Authorization: Bearer {你的token}`

#### 4.3 上传体检报告

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/health/report/upload`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Body** (form-data):
  - `file`: 文件（选择文件）
  - `reportType`: 报告类型（文本）
  - `reportDate`: 报告日期（格式：yyyy-MM-dd）
  - `hospitalName`: 医院名称（可选）

**Postman步骤：**
1. 方法：`POST`
2. URL：`http://localhost:8080/api/health/report/upload`
3. Headers：`Authorization: Bearer {你的token}`
4. Body标签页，选择 `form-data`：
   - Key: `file`, Type: `File`, Value: 选择文件（PDF、图片等）
   - Key: `reportType`, Type: `Text`, Value: `血常规+血脂检查`
   - Key: `reportDate`, Type: `Text`, Value: `2024-01-20`
   - Key: `hospitalName`, Type: `Text`, Value: `市人民医院`（可选）

**curl命令：**
```bash
curl -X POST http://localhost:8080/api/health/report/upload \
  -H "Authorization: Bearer {你的token}" \
  -F "file=@/path/to/report.pdf" \
  -F "reportType=血常规+血脂检查" \
  -F "reportDate=2024-01-20" \
  -F "hospitalName=市人民医院"
```

#### 4.4 AI报告解读

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/health/report/ai-analysis`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  Content-Type: application/x-www-form-urlencoded
  ```
- **Body** (form-data):
  - `reportId`: 报告ID（必填）

**Postman步骤：**
1. 方法：`POST`
2. URL：`http://localhost:8080/api/health/report/ai-analysis`
3. Headers：`Authorization: Bearer {你的token}`
4. Body标签页，选择 `form-data` 或 `x-www-form-urlencoded`：
   - Key: `reportId`, Value: `123456`（替换为实际报告ID）

**预期响应：**
```json
{
  "code": 200,
  "message": "解读成功",
  "data": null
}
```

---

### 五、慢病管理接口

#### 5.1 获取慢病列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/chronic-disease/list`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/chronic-disease/list?current=1&size=10`
3. Headers：`Authorization: Bearer {你的token}`

#### 5.2 获取慢病详情

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/chronic-disease/{id}`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/chronic-disease/123456`（替换为实际慢病ID）
3. Headers：`Authorization: Bearer {你的token}`

#### 5.3 获取干预方案

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/chronic-disease/intervention-plan`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `diseaseId`: 慢病ID（必填）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/chronic-disease/intervention-plan?diseaseId=123456`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "dietAdvice": [
      "减少钠盐摄入，每日不超过6克",
      "多食用富含钾的食物（香蕉、土豆）",
      "控制饱和脂肪摄入，增加不饱和脂肪酸（深海鱼、坚果）",
      "多吃富含膳食纤维的食物（燕麦、蔬菜）"
    ],
    "exercisePlan": [
      "每周5次有氧运动，每次30分钟",
      "推荐快走、游泳、骑车等中等强度运动",
      "避免剧烈运动和突然发力动作",
      "运动前后注意血压监测"
    ],
    "medicationReminder": [
      "按时服药，不可随意停药",
      "定期监测血压/血糖/血脂",
      "如有不适及时就医"
    ]
  }
}
```

#### 5.4 AI健康趋势预测

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/chronic-disease/trend-prediction`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `diseaseId`: 慢病ID（必填）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/chronic-disease/trend-prediction?diseaseId=123456`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "prediction": "根据您的健康数据趋势分析，预计未来一个月内指标将逐步改善",
    "confidence": 0.85,
    "suggestions": "继续保持当前治疗方案，注意饮食和运动"
  }
}
```

---

### 六、消息提醒接口

#### 6.1 获取用药提醒列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/reminder/medication`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/reminder/medication?current=1&size=10`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": "...",
        "patientId": "1",
        "medicationName": "阿司匹林",
        "dosage": "100mg",
        "frequency": "每日1次",
        "reminderTime": "08:00:00",
        "startDate": "2024-01-01",
        "endDate": "2024-01-31",
        "status": "ACTIVE",
        "isRead": 0
      }
    ],
    "total": 5,
    "size": 10,
    "current": 1
  }
}
```

#### 6.2 获取复诊提醒列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/reminder/appointment`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/reminder/appointment?current=1&size=10`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": "...",
        "patientId": "1",
        "appointmentDate": "2024-02-01T10:00:00",
        "hospitalName": "市人民医院",
        "department": "心内科",
        "doctorName": "李医生",
        "status": "PENDING",
        "isRead": 0
      }
    ],
    "total": 3,
    "size": 10,
    "current": 1
  }
}
```

#### 6.3 标记提醒为已读

**请求信息：**
- **方法**: `PUT`
- **URL**: `http://localhost:8080/api/reminder/{id}/read`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `type`: 提醒类型（必填）：`medication` 或 `appointment`

**Postman步骤：**
1. 方法：`PUT`
2. URL：`http://localhost:8080/api/reminder/123456/read?type=medication`
3. Headers：`Authorization: Bearer {你的token}`

**curl命令：**
```bash
curl -X PUT "http://localhost:8080/api/reminder/123456/read?type=medication" \
  -H "Authorization: Bearer {你的token}"
```

---

### 七、档案管理接口

#### 7.1 获取过敏史

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/profile/allergy-history`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/profile/allergy-history`
3. Headers：`Authorization: Bearer {你的token}`

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": "...",
      "patientId": "1",
      "allergen": "青霉素",
      "reaction": "皮疹、呼吸困难",
      "severity": "SEVERE",
      "recordedDate": "2020-03-15"
    }
  ]
}
```

#### 7.2 添加过敏史

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/profile/allergy-history`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  Content-Type: application/json
  ```
- **Body** (JSON):
  ```json
  {
    "allergen": "青霉素",
    "reaction": "皮疹、呼吸困难",
    "severity": "SEVERE",
    "recordedDate": "2020-03-15"
  }
  ```

**Postman步骤：**
1. 方法：`POST`
2. URL：`http://localhost:8080/api/profile/allergy-history`
3. Headers：
   - `Authorization: Bearer {你的token}`
   - `Content-Type: application/json`
4. Body（raw JSON）：
   ```json
   {
     "allergen": "青霉素",
     "reaction": "皮疹、呼吸困难",
     "severity": "SEVERE",
     "recordedDate": "2020-03-15"
   }
   ```

**severity可选值：**
- `MILD` - 轻度
- `MODERATE` - 中度
- `SEVERE` - 严重

#### 7.3 获取过往病史

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/profile/medical-history`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": "...",
      "patientId": "1",
      "diseaseName": "高血压",
      "diagnosedDate": "2022-05-10",
      "status": "TREATING",
      "hospitalName": "市人民医院",
      "doctorName": "李医生"
    }
  ]
}
```

#### 7.4 获取亲友列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/profile/family-members`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```

**预期响应：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": "...",
      "patientId": "1",
      "memberName": "张三",
      "relation": "SPOUSE",
      "memberPhone": "13800138000",
      "permissionLevel": "VIEW"
    }
  ]
}
```

#### 7.5 添加亲友

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/profile/family-members`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  Content-Type: application/json
  ```
- **Body** (JSON):
  ```json
  {
    "memberName": "张三",
    "relation": "SPOUSE",
    "memberPhone": "13800138000",
    "permissionLevel": "VIEW"
  }
  ```

**relation可选值：**
- `SPOUSE` - 配偶
- `CHILD` - 子女
- `PARENT` - 父母
- `OTHER` - 其他

**permissionLevel可选值：**
- `VIEW` - 查看
- `MANAGE` - 管理

---

### 八、其他功能接口

#### 8.1 获取医嘱列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/doctor/advice`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/doctor/advice?current=1&size=10`
3. Headers：`Authorization: Bearer {你的token}`

#### 8.2 获取健康课程列表

**请求信息：**
- **方法**: `GET`
- **URL**: `http://localhost:8080/api/health/course/list`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `current`: 当前页（默认：1）
  - `size`: 每页数量（默认：10）
  - `courseType`: 课程类型（可选）

**Postman步骤：**
1. 方法：`GET`
2. URL：`http://localhost:8080/api/health/course/list?current=1&size=10`
3. Headers：`Authorization: Bearer {你的token}`

---

### 九、文件上传接口

#### 9.1 上传文件

**请求信息：**
- **方法**: `POST`
- **URL**: `http://localhost:8080/api/file/upload`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Body** (form-data):
  - `file`: 文件（选择文件）
  - `subDir`: 子目录（可选，默认：common）

**Postman步骤：**
1. 方法：`POST`
2. URL：`http://localhost:8080/api/file/upload`
3. Headers：`Authorization: Bearer {你的token}`
4. Body标签页，选择 `form-data`：
   - Key: `file`, Type: `File`, Value: 选择文件
   - Key: `subDir`, Type: `Text`, Value: `reports`（可选）

**支持的文件类型：**
- 图片：jpg, jpeg, png, gif
- 文档：pdf, doc, docx

**文件大小限制：** 10MB

**预期响应：**
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

#### 9.2 删除文件

**请求信息：**
- **方法**: `DELETE`
- **URL**: `http://localhost:8080/api/file/delete`
- **Headers**: 
  ```
  Authorization: Bearer {你的token}
  ```
- **Query参数**:
  - `fileUrl`: 文件URL（必填）

**Postman步骤：**
1. 方法：`DELETE`
2. URL：`http://localhost:8080/api/file/delete?fileUrl=/upload/reports/2024/01/20/uuid.pdf`
3. Headers：`Authorization: Bearer {你的token}`

---

## 🧪 测试建议

### 1. 测试顺序

建议按以下顺序测试：

1. ✅ **认证接口**（必须先测试，获取Token）
   - 登录 → 获取用户信息 → 登出

2. ✅ **基础数据接口**
   - 患者信息 → 健康画像摘要

3. ✅ **健康指标接口**
   - 添加指标 → 查询列表 → 查询趋势 → 查询异常

4. ✅ **其他功能接口**
   - 体检报告 → 慢病管理 → 消息提醒 → 档案管理

### 2. 使用Postman Collection

**创建Collection：**
1. 在Postman中创建新Collection：`医疗健康管理系统API`
2. 创建文件夹组织接口：
   - 认证相关
   - 患者管理
   - 健康指标
   - 体检报告
   - 慢病管理
   - 消息提醒
   - 档案管理
   - 其他功能

**设置环境变量：**
1. 创建环境：`开发环境`
2. 添加变量：
   - `base_url`: `http://localhost:8080`
   - `token`: （登录后手动设置）
3. 在请求URL中使用：`{{base_url}}/api/auth/login`
4. 在Headers中使用：`Bearer {{token}}`

### 3. 使用Knife4j文档测试（最简单）

1. 启动后端服务
2. 访问：http://localhost:8080/doc.html
3. 在文档页面直接测试：
   - 点击接口
   - 点击"调试"
   - 填写参数
   - 点击"发送请求"
   - 查看响应

**优点：**
- 无需配置Token（自动处理）
- 界面友好
- 可以直接查看API文档

### 4. 测试数据准备

**建议先插入一些测试数据：**

```sql
-- 插入测试患者
INSERT INTO health_patient (id, user_id, name, gender, phone) 
VALUES ('1', '1', '测试患者', 1, '13800138000');

-- 插入健康指标数据
INSERT INTO health_metric (id, patient_id, metric_type, metric_value, record_date, normal_range_min, normal_range_max, status)
VALUES 
('1', '1', 'BLOOD_PRESSURE', 135.00, '2024-01-20', 90.00, 140.00, 'NORMAL'),
('2', '1', 'BLOOD_SUGAR', 6.2, '2024-01-20', 3.9, 6.1, 'ABNORMAL'),
('3', '1', 'CHOLESTEROL', 6.2, '2024-01-20', 3.1, 5.7, 'ABNORMAL');

-- 插入慢病数据
INSERT INTO chronic_disease (id, patient_id, disease_type, diagnosed_date, status)
VALUES ('1', '1', 'HYPERTENSION', '2022-05-10', 'TREATING');
```

---

## ⚠️ 常见错误处理

### 错误1：401 Unauthorized

**原因：** Token无效或过期

**解决方法：**
1. 重新登录获取新Token
2. 检查Token格式：`Bearer {token}`（注意Bearer后面有空格）
3. 检查Token是否过期（24小时有效期）

### 错误2：400 Bad Request

**原因：** 请求参数错误

**解决方法：**
1. 检查请求体格式（JSON格式是否正确）
2. 检查必填参数是否都提供了
3. 检查参数类型是否正确（日期格式：yyyy-MM-dd）

### 错误3：500 Internal Server Error

**原因：** 服务器内部错误

**解决方法：**
1. 查看后端日志：`logs/health-management.log`
2. 检查数据库连接是否正常
3. 检查数据是否存在（如查询不存在的ID）

### 错误4：文件上传失败

**原因：** 文件大小或类型不符合要求

**解决方法：**
1. 检查文件大小是否超过10MB
2. 检查文件类型是否支持（jpg, jpeg, png, gif, pdf, doc, docx）
3. 检查upload目录是否存在且有写入权限

---

## 📊 测试检查清单

### 认证相关
- [ ] 登录接口（获取Token）
- [ ] 获取用户信息
- [ ] 登出接口

### 患者管理
- [ ] 获取患者信息
- [ ] 更新患者信息
- [ ] 获取健康画像摘要

### 健康指标
- [ ] 添加健康指标（血压、血糖、血脂、BMI）
- [ ] 获取指标列表（分页、筛选）
- [ ] 获取趋势数据
- [ ] 获取异常指标

### 体检报告
- [ ] 上传体检报告
- [ ] 获取报告列表
- [ ] 获取报告详情
- [ ] AI报告解读

### 慢病管理
- [ ] 获取慢病列表
- [ ] 获取慢病详情
- [ ] 获取干预方案
- [ ] AI健康趋势预测

### 消息提醒
- [ ] 获取用药提醒列表
- [ ] 获取复诊提醒列表
- [ ] 标记提醒为已读

### 档案管理
- [ ] 获取过敏史
- [ ] 添加过敏史
- [ ] 获取过往病史
- [ ] 获取亲友列表
- [ ] 添加亲友

### 其他功能
- [ ] 获取医嘱列表
- [ ] 获取健康课程列表
- [ ] 文件上传
- [ ] 文件删除

---

## 🎯 快速测试脚本

### 使用curl批量测试（PowerShell）

创建文件 `test-api.ps1`：

```powershell
# 1. 登录获取Token
$loginResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/login" `
    -Method POST `
    -ContentType "application/json" `
    -Body '{"username":"admin","password":"123456"}'

$token = $loginResponse.data.token
Write-Host "Token: $token" -ForegroundColor Green

# 2. 设置Headers
$headers = @{
    "Authorization" = "Bearer $token"
    "Content-Type" = "application/json"
}

# 3. 测试获取患者信息
$patientInfo = Invoke-RestMethod -Uri "http://localhost:8080/api/patient/info" `
    -Method GET `
    -Headers $headers

Write-Host "患者信息: $($patientInfo | ConvertTo-Json)" -ForegroundColor Cyan

# 4. 测试添加健康指标
$metricBody = @{
    metricType = "BLOOD_PRESSURE"
    metricValue = 135.00
    recordDate = "2024-01-20"
    normalRangeMin = 90.00
    normalRangeMax = 140.00
} | ConvertTo-Json

$metricResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/health/metric/add" `
    -Method POST `
    -Headers $headers `
    -Body $metricBody

Write-Host "添加指标结果: $($metricResponse.message)" -ForegroundColor Cyan
```

运行：
```powershell
.\test-api.ps1
```

---

## 📝 测试记录模板

建议记录每次测试的结果：

| 接口名称 | 方法 | URL | 状态码 | 响应时间 | 测试结果 | 备注 |
|---------|------|-----|--------|---------|---------|------|
| 用户登录 | POST | /api/auth/login | 200 | 50ms | ✅ 通过 | Token获取成功 |
| 获取患者信息 | GET | /api/patient/info | 200 | 30ms | ✅ 通过 | 返回空数据（正常） |

---

## 🔗 相关资源

- **API文档**: http://localhost:8080/doc.html
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **项目文档**: [README.md](./README.md)
- **快速开始**: [QUICK_START.md](./QUICK_START.md)

---

**祝测试顺利！如有问题，请查看日志文件或联系开发团队。**
