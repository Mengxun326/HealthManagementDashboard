import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Button } from "../ui/button";
import { Activity, Heart } from "lucide-react";

const products = [
  {
    id: 1,
    name: "智能血压计",
    price: "¥299",
    description: "自动测量、数据同步、异常提醒",
    category: "监测设备",
  },
  {
    id: 2,
    name: "血糖仪套装",
    price: "¥399",
    description: "无痛采血、快速检测、蓝牙传输",
    category: "监测设备",
  },
  {
    id: 3,
    name: "智能手环",
    price: "¥599",
    description: "心率监测、睡眠分析、运动追踪",
    category: "健身器材",
  },
  {
    id: 4,
    name: "体脂秤",
    price: "¥199",
    description: "13项身体数据、APP同步、家庭共享",
    category: "监测设备",
  },
  {
    id: 5,
    name: "瑜伽垫套装",
    price: "¥159",
    description: "环保材质、防滑耐磨、含瑜伽砖",
    category: "健身器材",
  },
  {
    id: 6,
    name: "筋膜枪",
    price: "¥899",
    description: "深层按摩、肌肉放松、静音设计",
    category: "健身器材",
  },
];

export function PatientShop() {
  return (
    <div className="p-4 space-y-4">
      <Card className="bg-gradient-to-br from-green-50 to-emerald-50 border-green-200">
        <CardHeader>
          <CardTitle className="flex items-center gap-2 text-green-600">
            <Activity className="w-5 h-5" />
            健康商店
          </CardTitle>
        </CardHeader>
        <CardContent>
          <p className="text-sm text-gray-600">
            提供健身器材、监测设备等健康设备的购买服务
          </p>
        </CardContent>
      </Card>

      <div className="grid grid-cols-2 gap-4">
        {products.map((product) => (
          <Card key={product.id} className="overflow-hidden">
            <div className="h-32 bg-gradient-to-br from-blue-100 to-purple-100 flex items-center justify-center">
              {product.category === "监测设备" ? (
                <Heart className="w-12 h-12 text-blue-600" />
              ) : (
                <Activity className="w-12 h-12 text-purple-600" />
              )}
            </div>
            <CardContent className="p-3 space-y-2">
              <div>
                <h4 className="text-sm">{product.name}</h4>
                <p className="text-xs text-gray-500">{product.category}</p>
              </div>
              <p className="text-xs text-gray-600 line-clamp-2">{product.description}</p>
              <div className="flex items-center justify-between">
                <span className="text-red-600">{product.price}</span>
                <Button size="sm" variant="outline">
                  购买
                </Button>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}
