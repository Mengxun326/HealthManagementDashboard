import { SingleMetricChart } from "../SingleMetricChart";
import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { CircleAlert, TrendingUp, CircleDot } from "lucide-react";

// 血压数据
const bloodPressureData = [
  { date: "11/20", value: 135 },
  { date: "11/27", value: 132 },
  { date: "12/04", value: 138 },
  { date: "12/11", value: 140 },
  { date: "12/18", value: 130 },
  { date: "12/20", value: 128 },
];

// 血脂数据（总胆固醇）
const cholesterolData = [
  { date: "11/20", value: 6.2 },
  { date: "11/27", value: 6.0 },
  { date: "12/04", value: 6.3 },
  { date: "12/11", value: 6.1 },
  { date: "12/18", value: 5.8 },
  { date: "12/20", value: 5.5 },
];

// 血糖数据
const bloodSugarData = [
  { date: "11/20", value: 6.2 },
  { date: "11/27", value: 6.5 },
  { date: "12/04", value: 7.1 },
  { date: "12/11", value: 6.8 },
  { date: "12/18", value: 6.3 },
  { date: "12/20", value: 5.9 },
];

// BMI数据
const bmiData = [
  { date: "11/20", value: 26.5 },
  { date: "11/27", value: 26.3 },
  { date: "12/04", value: 26.8 },
  { date: "12/11", value: 26.2 },
  { date: "12/18", value: 25.9 },
  { date: "12/20", value: 25.6 },
];

const abnormalData = [
  {
    category: "血常规",
    items: [
      { name: "白细胞计数", value: "11.2×10⁹/L", normal: "4.0-10.0", status: "偏高" },
      { name: "血红蛋白", value: "110g/L", normal: "120-160", status: "偏低" },
    ],
  },
  {
    category: "血脂",
    items: [
      { name: "总胆固醇", value: "6.2mmol/L", normal: "3.1-5.7", status: "偏高" },
      { name: "低密度脂蛋白", value: "3.8mmol/L", normal: "0-3.4", status: "偏高" },
    ],
  },
];

export function PatientHome() {
  const normalCount = 8; // 正常指标数
  const abnormalCount = 4; // 异常指标数
  const totalCount = normalCount + abnormalCount;

  return (
    <div className="p-4 space-y-4">
      {/* 个人健康画像 */}
      <Card className="bg-gradient-to-br from-blue-50 to-blue-100 border-blue-200">
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <TrendingUp className="w-5 h-5 text-blue-600" />
            个人健康画像
          </CardTitle>
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
            {/* 健康红绿灯 */}
            <div className="flex items-center justify-between">
              <div className="flex items-center gap-4">
                <div className="flex gap-2">
                  <div className="w-8 h-8 rounded-full bg-red-500 flex items-center justify-center">
                    <CircleDot className="w-5 h-5 text-white" />
                  </div>
                  <div className="w-8 h-8 rounded-full bg-yellow-400 flex items-center justify-center opacity-30">
                    <CircleDot className="w-5 h-5 text-white" />
                  </div>
                  <div className="w-8 h-8 rounded-full bg-green-500 flex items-center justify-center opacity-30">
                    <CircleDot className="w-5 h-5 text-white" />
                  </div>
                </div>
                <div>
                  <p className="text-sm text-gray-600">健康状态</p>
                  <p className="text-red-600">需要注意</p>
                </div>
              </div>
            </div>

            {/* 指标统计 */}
            <div className="grid grid-cols-2 gap-3">
              <div className="bg-white/60 p-3 rounded-lg">
                <div className="text-sm text-gray-600">正常指标</div>
                <div className="text-2xl text-green-600">{normalCount}</div>
                <div className="text-xs text-gray-500">占 {((normalCount/totalCount)*100).toFixed(0)}%</div>
              </div>
              <div className="bg-white/60 p-3 rounded-lg">
                <div className="text-sm text-gray-600">异常指标</div>
                <div className="text-2xl text-red-600">{abnormalCount}</div>
                <div className="text-xs text-gray-500">占 {((abnormalCount/totalCount)*100).toFixed(0)}%</div>
              </div>
            </div>

            <div className="text-sm text-gray-600">
              您的健康状况需要关注，血脂和血常规存在异常指标。建议调整饮食结构，增加运动量，并及时复诊。
            </div>
          </div>
        </CardContent>
      </Card>

      {/* 健康趋势图 - 4个纵向排布的变化曲线 */}
      <div className="space-y-4">
        <h3 className="text-lg">健康指标变化趋势</h3>
        
        {/* 血压变化 */}
        <SingleMetricChart
          title="血压变化（收缩压）"
          data={bloodPressureData}
          normalRange={{ min: 90, max: 140 }}
          unit="mmHg"
          color="#3b82f6"
        />

        {/* 血脂变化 */}
        <SingleMetricChart
          title="血脂变化（总胆固醇）"
          data={cholesterolData}
          normalRange={{ min: 3.1, max: 5.2 }}
          unit="mmol/L"
          color="#f59e0b"
        />

        {/* 血糖变化 */}
        <SingleMetricChart
          title="血糖变化（空腹血糖）"
          data={bloodSugarData}
          normalRange={{ min: 3.9, max: 6.1 }}
          unit="mmol/L"
          color="#ef4444"
        />

        {/* BMI变化 */}
        <SingleMetricChart
          title="BMI变化"
          data={bmiData}
          normalRange={{ min: 18.5, max: 24 }}
          unit="kg/m²"
          color="#8b5cf6"
        />
      </div>

      {/* 异常数据解读 */}
      <Card>
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <CircleAlert className="w-5 h-5 text-orange-600" />
            异常数据解读
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          {abnormalData.map((category, idx) => (
            <div key={idx} className="space-y-2">
              <h4 className="text-blue-600">{category.category}</h4>
              <div className="space-y-2">
                {category.items.map((item, itemIdx) => (
                  <div key={itemIdx} className="bg-orange-50 p-3 rounded-lg border border-orange-200">
                    <div className="flex items-center justify-between mb-1">
                      <span>{item.name}</span>
                      <span className="px-2 py-1 bg-orange-200 text-orange-800 text-xs rounded">
                        {item.status}
                      </span>
                    </div>
                    <div className="text-sm text-gray-600">
                      <span>当前值: {item.value}</span>
                      <span className="ml-4">正常范围: {item.normal}</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ))}
          <div className="bg-blue-50 p-3 rounded-lg border border-blue-200 text-sm">
            <p className="text-gray-700">
              <strong>AI健康建议：</strong>
              建议您在日常饮食中减少高脂肪食物摄入，多食用富含纤维的蔬菜水果。每周进行3-5次中等强度运动，每次30分钟以上。建议一个月后复查血常规和血脂。
            </p>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}