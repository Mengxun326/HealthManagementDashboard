import { SingleMetricChart } from "../SingleMetricChart";
import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { TrendingUp, Heart, Activity } from "lucide-react";
import { useState } from "react";

// 模拟患者当前指标数据（用于判断哪些模块显示红色）
const patientStatus = {
  hypertension: true, // 高血压
  hyperlipidemia: true, // 高血脂
  diabetes: false, // 高血糖
};

// 7个慢病管理模块
const chronicDiseaseModules = [
  { 
    id: "hypertension", 
    title: "高血压", 
    condition: patientStatus.hypertension,
    description: "收缩压 ≥ 140 mmHg 或舒张压 ≥ 90 mmHg"
  },
  { 
    id: "diabetes", 
    title: "高血糖", 
    condition: patientStatus.diabetes,
    description: "空腹血糖 ≥ 7.0 mmol/L 或餐后2h血糖 ≥ 11.1 mmol/L"
  },
  { 
    id: "hyperlipidemia", 
    title: "高血脂", 
    condition: patientStatus.hyperlipidemia,
    description: "总胆固醇 ≥ 6.2 mmol/L"
  },
  { 
    id: "hypertension_diabetes", 
    title: "高血压+高血糖", 
    condition: patientStatus.hypertension && patientStatus.diabetes,
    description: "同时患有高血压和高血糖"
  },
  { 
    id: "diabetes_hyperlipidemia", 
    title: "高血糖+高血脂", 
    condition: patientStatus.diabetes && patientStatus.hyperlipidemia,
    description: "同时患有高血糖和高血脂"
  },
  { 
    id: "hypertension_hyperlipidemia", 
    title: "高血压+高血脂", 
    condition: patientStatus.hypertension && patientStatus.hyperlipidemia,
    description: "同时患有高血压和高血脂"
  },
  { 
    id: "all_three", 
    title: "高血压+高血糖+高血脂", 
    condition: patientStatus.hypertension && patientStatus.diabetes && patientStatus.hyperlipidemia,
    description: "同时患有高血压、高血糖和高血脂"
  },
];

// 血压趋势数据
const bloodPressureData = [
  { date: "12/14", value: 135 },
  { date: "12/15", value: 132 },
  { date: "12/16", value: 138 },
  { date: "12/17", value: 130 },
  { date: "12/18", value: 128 },
  { date: "12/19", value: 125 },
  { date: "12/20", value: 128 },
];

// 血脂趋势数据
const cholesterolData = [
  { date: "12/14", value: 6.2 },
  { date: "12/15", value: 6.0 },
  { date: "12/16", value: 6.3 },
  { date: "12/17", value: 6.1 },
  { date: "12/18", value: 5.8 },
  { date: "12/19", value: 5.6 },
  { date: "12/20", value: 5.5 },
];

const interventionPlans = [
  {
    type: "饮食建议",
    items: [
      "减少钠盐摄入，每日不超过6克",
      "多食用富含钾的食物（香蕉、土豆）",
      "控制饱和脂肪摄入，增加不饱和脂肪酸（深海鱼、坚果）",
      "多吃富含膳食纤维的食物（燕麦、蔬菜）"
    ],
  },
  {
    type: "运动计划",
    items: [
      "每周5次有氧运动，每次30分钟",
      "推荐快走、游泳、骑车等中等强度运动",
      "避免剧烈运动和突然发力动作",
      "运动前后注意血压监测"
    ],
  },
  {
    type: "用药提醒",
    items: [
      "降压药：每日1次，早餐后服用",
      "他汀类药物：每日1次，晚餐后服用",
      "按时服药，不可自行停药或调整剂量"
    ],
  },
];

export function PatientChronicDisease() {
  const [selectedModule, setSelectedModule] = useState<string | null>(
    chronicDiseaseModules.find(m => m.condition)?.id || null
  );

  // 获取选中模块的详细信息
  const getModuleContent = (moduleId: string | null) => {
    if (!moduleId) return null;

    // 根据不同模块返回对应的内容
    if (moduleId.includes("hypertension") && moduleId.includes("hyperlipidemia")) {
      return {
        charts: [
          <SingleMetricChart
            key="bp"
            title="血压变化（收缩压）"
            data={bloodPressureData}
            normalRange={{ min: 90, max: 140 }}
            unit="mmHg"
            color="#3b82f6"
          />,
          <SingleMetricChart
            key="chol"
            title="血脂变化（总胆固醇）"
            data={cholesterolData}
            normalRange={{ min: 3.1, max: 5.2 }}
            unit="mmol/L"
            color="#f59e0b"
          />
        ]
      };
    } else if (moduleId === "hypertension") {
      return {
        charts: [
          <SingleMetricChart
            key="bp"
            title="血压变化（收缩压）"
            data={bloodPressureData}
            normalRange={{ min: 90, max: 140 }}
            unit="mmHg"
            color="#3b82f6"
          />
        ]
      };
    } else if (moduleId === "hyperlipidemia") {
      return {
        charts: [
          <SingleMetricChart
            key="chol"
            title="血脂变化（总胆固醇）"
            data={cholesterolData}
            normalRange={{ min: 3.1, max: 5.2 }}
            unit="mmol/L"
            color="#f59e0b"
          />
        ]
      };
    }
    
    return { charts: [] };
  };

  const moduleContent = getModuleContent(selectedModule);

  return (
    <div className="p-4 space-y-4">
      <Card className="bg-gradient-to-br from-red-50 to-pink-50 border-red-200">
        <CardHeader>
          <CardTitle className="flex items-center gap-2 text-red-600">
            <Heart className="w-5 h-5" />
            慢性病管理专项
          </CardTitle>
        </CardHeader>
        <CardContent>
          <p className="text-sm text-gray-600">
            针对"五高"（高血压、高血脂、高血糖、高尿酸、高体重）的个性化管理
          </p>
        </CardContent>
      </Card>

      {/* 7个功能模块 */}
      <div className="grid grid-cols-2 gap-3">
        {chronicDiseaseModules.map((module) => (
          <Card
            key={module.id}
            className={`cursor-pointer transition-all ${
              module.condition
                ? "bg-red-50 border-2 border-red-400"
                : "bg-green-50 border-2 border-green-400"
            } ${selectedModule === module.id ? "ring-2 ring-blue-500" : ""}`}
            onClick={() => setSelectedModule(module.id)}
          >
            <CardContent className="p-4">
              <div className="flex items-start justify-between mb-2">
                <h4 className={`text-sm ${module.condition ? "text-red-700" : "text-green-700"}`}>
                  {module.title}
                </h4>
                <div
                  className={`w-3 h-3 rounded-full ${
                    module.condition ? "bg-red-500" : "bg-green-500"
                  }`}
                />
              </div>
              <p className="text-xs text-gray-600">{module.description}</p>
              {module.condition && (
                <div className="mt-2 px-2 py-1 bg-red-100 text-red-700 text-xs rounded">
                  需要关注
                </div>
              )}
            </CardContent>
          </Card>
        ))}
      </div>

      {/* 选中模块的详细内容 */}
      {selectedModule && moduleContent && (
        <>
          {/* 健康状态趋势图 */}
          {moduleContent.charts.length > 0 && (
            <Card>
              <CardHeader>
                <CardTitle className="flex items-center gap-2">
                  <Activity className="w-5 h-5 text-blue-600" />
                  健康状态趋势
                </CardTitle>
              </CardHeader>
              <CardContent className="space-y-4">
                {moduleContent.charts}
              </CardContent>
            </Card>
          )}

          {/* AI健康趋势预测 */}
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <TrendingUp className="w-5 h-5 text-green-600" />
                AI健康趋势预测
              </CardTitle>
            </CardHeader>
            <CardContent>
              <div className="space-y-3">
                <div className="flex items-center justify-between p-3 bg-green-50 rounded-lg">
                  <span className="text-sm">预测未来一周健康指数</span>
                  <span className="text-green-600">稳定趋势</span>
                </div>
                <div className="flex items-center justify-between p-3 bg-yellow-50 rounded-lg">
                  <span className="text-sm">心血管风险评估</span>
                  <span className="text-yellow-600">中等风险</span>
                </div>
                <p className="text-sm text-gray-600 p-3 bg-gray-50 rounded-lg">
                  根据您近期的数据分析，血压和血脂控制有所改善。如果继续保持良好的生活习惯和按时服药，预计在未来3个月内各项指标将逐步达到理想范围。建议加强运动锻炼，进一步改善代谢水平。
                </p>
              </div>
            </CardContent>
          </Card>

          {/* 个性化干预方案 */}
          <Card>
            <CardHeader>
              <CardTitle>个性化干预方案</CardTitle>
            </CardHeader>
            <CardContent className="space-y-4">
              {interventionPlans.map((plan, idx) => (
                <div key={idx} className="space-y-2">
                  <h4 className="text-blue-600">{plan.type}</h4>
                  <ul className="space-y-1">
                    {plan.items.map((item, itemIdx) => (
                      <li key={itemIdx} className="text-sm text-gray-600 flex items-start gap-2">
                        <span className="text-blue-600 mt-1">•</span>
                        <span>{item}</span>
                      </li>
                    ))}
                  </ul>
                </div>
              ))}
            </CardContent>
          </Card>
        </>
      )}
    </div>
  );
}