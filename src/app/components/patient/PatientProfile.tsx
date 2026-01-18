import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Button } from "../ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "../ui/dialog";
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import { Avatar, AvatarFallback } from "../ui/avatar";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "../ui/tabs";
import { User, Users, Settings, Heart, Phone, Bell, FolderOpen, FileText, AlertCircle, ClipboardList } from "lucide-react";
import { useState } from "react";

const familyMembers = [
  { id: 1, name: "张三", relation: "配偶", phone: "138****8888" },
  { id: 2, name: "李四", relation: "子女", phone: "139****9999" },
];

// 历史报告数据
const historicalReports = [
  {
    id: 1,
    date: "2024-12-15",
    type: "血常规+血脂检查",
    hospital: "市人民医院",
    status: "已解读",
  },
  {
    id: 2,
    date: "2024-11-20",
    type: "全面体检",
    hospital: "体检中心",
    status: "已解读",
  },
  {
    id: 3,
    date: "2024-10-10",
    type: "心电图检查",
    hospital: "市人民医院",
    status: "已解读",
  },
];

// 过敏史数据
const allergyHistory = [
  {
    id: 1,
    allergen: "青霉素",
    reaction: "皮疹、呼吸困难",
    severity: "严重",
    recordedDate: "2020-03-15",
  },
  {
    id: 2,
    allergen: "海鲜（虾、蟹）",
    reaction: "皮肤瘙痒",
    severity: "轻度",
    recordedDate: "2018-06-20",
  },
];

// 过往病史数据
const medicalHistory = [
  {
    id: 1,
    disease: "高血压",
    diagnosedDate: "2022-05-10",
    status: "治疗中",
    hospital: "市人民医院",
    doctor: "李医生",
  },
  {
    id: 2,
    disease: "高血脂",
    diagnosedDate: "2022-05-10",
    status: "治疗中",
    hospital: "市人民医院",
    doctor: "李医生",
  },
  {
    id: 3,
    disease: "胃炎",
    diagnosedDate: "2019-08-15",
    status: "已治愈",
    hospital: "中心医院",
    doctor: "王医生",
  },
];

export function PatientProfile() {
  const [archiveDialogOpen, setArchiveDialogOpen] = useState(false);
  const [selectedFamilyMember, setSelectedFamilyMember] = useState<number | null>(null);

  return (
    <div className="p-4 space-y-4">
      {/* 用户信息卡片 */}
      <Card>
        <CardContent className="pt-6">
          <div className="flex items-center gap-4 mb-4">
            <Avatar className="w-16 h-16">
              <AvatarFallback className="bg-blue-100 text-blue-600 text-xl">王</AvatarFallback>
            </Avatar>
            <div>
              <h3>王小明</h3>
              <p className="text-sm text-gray-500">138****1234</p>
            </div>
          </div>
          <Button variant="outline" className="w-full">
            <User className="w-4 h-4 mr-2" />
            编辑个人信息
          </Button>
        </CardContent>
      </Card>

      {/* 健康管家 - 只保留AI健康助手 */}
      <Card>
        <CardContent className="pt-6 space-y-3">
          <div className="flex items-center gap-2 mb-3">
            <Heart className="w-5 h-5 text-purple-600" />
            <h4>我的健康管家</h4>
          </div>
          <div className="p-4 bg-gradient-to-br from-purple-50 to-pink-50 rounded-lg border-2 border-purple-200">
            <div className="flex items-center gap-3 mb-3">
              <div className="w-12 h-12 bg-purple-600 rounded-full flex items-center justify-center text-white text-xl">
                AI
              </div>
              <div className="flex-1">
                <h4 className="text-purple-900">AI健康助手</h4>
                <p className="text-sm text-purple-700">24小时智能健康管理服务</p>
              </div>
              <span className="px-3 py-1 bg-purple-600 text-white text-xs rounded-full">使用中</span>
            </div>
            <div className="grid grid-cols-3 gap-2 text-center text-sm">
              <div className="bg-white/50 rounded p-2">
                <div className="text-purple-600">156</div>
                <div className="text-xs text-gray-600">咨询次数</div>
              </div>
              <div className="bg-white/50 rounded p-2">
                <div className="text-purple-600">42</div>
                <div className="text-xs text-gray-600">健康建议</div>
              </div>
              <div className="bg-white/50 rounded p-2">
                <div className="text-purple-600">28天</div>
                <div className="text-xs text-gray-600">服务天数</div>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>

      {/* 档案管理 */}
      <Card>
        <CardContent className="pt-6 space-y-3">
          <div className="flex items-center gap-2 mb-3">
            <FolderOpen className="w-5 h-5 text-blue-600" />
            <h4>档案管理</h4>
          </div>
          <Dialog open={archiveDialogOpen} onOpenChange={setArchiveDialogOpen}>
            <DialogTrigger asChild>
              <Button variant="outline" className="w-full">
                <FolderOpen className="w-4 h-4 mr-2" />
                查看我的健康档案
              </Button>
            </DialogTrigger>
            <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
              <DialogHeader>
                <DialogTitle>健康档案管理</DialogTitle>
              </DialogHeader>
              <Tabs defaultValue="reports" className="w-full">
                <TabsList className="grid w-full grid-cols-3">
                  <TabsTrigger value="reports">历史报告</TabsTrigger>
                  <TabsTrigger value="allergies">过敏史</TabsTrigger>
                  <TabsTrigger value="history">过往病史</TabsTrigger>
                </TabsList>

                {/* 历史报告 */}
                <TabsContent value="reports" className="space-y-3">
                  <div className="flex items-center gap-2 mb-2">
                    <FileText className="w-4 h-4 text-blue-600" />
                    <h4 className="text-sm">历史检查报告</h4>
                  </div>
                  {historicalReports.map((report) => (
                    <Card key={report.id} className="bg-gray-50">
                      <CardContent className="p-4">
                        <div className="flex items-start justify-between">
                          <div className="flex-1">
                            <h4 className="mb-1">{report.type}</h4>
                            <div className="text-sm text-gray-600 space-y-1">
                              <p>{report.hospital}</p>
                              <p className="text-xs">{report.date}</p>
                            </div>
                          </div>
                          <div className="flex flex-col items-end gap-2">
                            <span className="px-2 py-1 bg-green-100 text-green-700 text-xs rounded">
                              {report.status}
                            </span>
                            <Button variant="ghost" size="sm">查看详情</Button>
                          </div>
                        </div>
                      </CardContent>
                    </Card>
                  ))}
                </TabsContent>

                {/* 过敏史 */}
                <TabsContent value="allergies" className="space-y-3">
                  <div className="flex items-center gap-2 mb-2">
                    <AlertCircle className="w-4 h-4 text-red-600" />
                    <h4 className="text-sm">过敏史记录</h4>
                  </div>
                  {allergyHistory.length > 0 ? (
                    allergyHistory.map((allergy) => (
                      <Card key={allergy.id} className="bg-red-50 border-red-200">
                        <CardContent className="p-4">
                          <div className="flex items-start justify-between mb-2">
                            <div className="flex-1">
                              <h4 className="text-red-900 mb-1">{allergy.allergen}</h4>
                              <div className="text-sm text-gray-700">
                                <p>过敏反应：{allergy.reaction}</p>
                                <p className="text-xs text-gray-500 mt-1">记录时间：{allergy.recordedDate}</p>
                              </div>
                            </div>
                            <span
                              className={`px-2 py-1 text-xs rounded ${
                                allergy.severity === "严重"
                                  ? "bg-red-600 text-white"
                                  : "bg-yellow-100 text-yellow-700"
                              }`}
                            >
                              {allergy.severity}
                            </span>
                          </div>
                        </CardContent>
                      </Card>
                    ))
                  ) : (
                    <div className="text-center text-gray-500 py-8">
                      <AlertCircle className="w-12 h-12 mx-auto mb-2 opacity-30" />
                      <p>暂无过敏史记录</p>
                    </div>
                  )}
                  <Button variant="outline" className="w-full">
                    <AlertCircle className="w-4 h-4 mr-2" />
                    添加过敏史
                  </Button>
                </TabsContent>

                {/* 过往病史 */}
                <TabsContent value="history" className="space-y-3">
                  <div className="flex items-center gap-2 mb-2">
                    <ClipboardList className="w-4 h-4 text-blue-600" />
                    <h4 className="text-sm">过往病史</h4>
                  </div>
                  {medicalHistory.map((record) => (
                    <Card key={record.id} className="bg-gray-50">
                      <CardContent className="p-4">
                        <div className="flex items-start justify-between">
                          <div className="flex-1">
                            <h4 className="mb-1">{record.disease}</h4>
                            <div className="text-sm text-gray-600 space-y-1">
                              <p>确诊时间：{record.diagnosedDate}</p>
                              <p>{record.hospital} · {record.doctor}</p>
                            </div>
                          </div>
                          <span
                            className={`px-2 py-1 text-xs rounded ${
                              record.status === "治疗中"
                                ? "bg-blue-100 text-blue-700"
                                : "bg-green-100 text-green-700"
                            }`}
                          >
                            {record.status}
                          </span>
                        </div>
                      </CardContent>
                    </Card>
                  ))}
                  <Button variant="outline" className="w-full">
                    <ClipboardList className="w-4 h-4 mr-2" />
                    添加病史记录
                  </Button>
                </TabsContent>
              </Tabs>
            </DialogContent>
          </Dialog>
        </CardContent>
      </Card>

      {/* 亲友管理 */}
      <Card>
        <CardContent className="pt-6 space-y-3">
          <div className="flex items-center justify-between mb-3">
            <div className="flex items-center gap-2">
              <Users className="w-5 h-5 text-blue-600" />
              <h4>亲友账号</h4>
            </div>
            <Dialog>
              <DialogTrigger asChild>
                <Button size="sm" variant="outline">
                  + 添加
                </Button>
              </DialogTrigger>
              <DialogContent>
                <DialogHeader>
                  <DialogTitle>绑定亲友账号</DialogTitle>
                </DialogHeader>
                <div className="space-y-4">
                  <div>
                    <Label>手机号</Label>
                    <Input placeholder="输入亲友手机号" />
                  </div>
                  <div>
                    <Label>关系</Label>
                    <Input placeholder="例如：配偶、子女、父母" />
                  </div>
                  <p className="text-sm text-gray-500">
                    绑定后需要通过人脸识别验证
                  </p>
                  <Button className="w-full">发送绑定申请</Button>
                </div>
              </DialogContent>
            </Dialog>
          </div>
          <div className="space-y-2">
            {familyMembers.map((member) => (
              <div key={member.id} className="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
                <div className="flex items-center gap-3">
                  <Avatar className="w-10 h-10">
                    <AvatarFallback className="bg-blue-100 text-blue-600">
                      {member.name[0]}
                    </AvatarFallback>
                  </Avatar>
                  <div>
                    <p className="text-sm">{member.name}</p>
                    <p className="text-xs text-gray-500">{member.relation} · {member.phone}</p>
                  </div>
                </div>
                <Dialog>
                  <DialogTrigger asChild>
                    <Button 
                      variant="ghost" 
                      size="sm"
                      onClick={() => setSelectedFamilyMember(member.id)}
                    >
                      查看
                    </Button>
                  </DialogTrigger>
                  <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
                    <DialogHeader>
                      <DialogTitle>{member.name}的健康档案</DialogTitle>
                    </DialogHeader>
                    <Tabs defaultValue="reports" className="w-full">
                      <TabsList className="grid w-full grid-cols-3">
                        <TabsTrigger value="reports">历史报告</TabsTrigger>
                        <TabsTrigger value="allergies">过敏史</TabsTrigger>
                        <TabsTrigger value="history">过往病史</TabsTrigger>
                      </TabsList>

                      {/* 亲友的历史报告 */}
                      <TabsContent value="reports" className="space-y-3">
                        <div className="flex items-center gap-2 mb-2">
                          <FileText className="w-4 h-4 text-blue-600" />
                          <h4 className="text-sm">历史检查报告</h4>
                        </div>
                        {historicalReports.slice(0, 2).map((report) => (
                          <Card key={report.id} className="bg-gray-50">
                            <CardContent className="p-4">
                              <div className="flex items-start justify-between">
                                <div className="flex-1">
                                  <h4 className="mb-1">{report.type}</h4>
                                  <div className="text-sm text-gray-600 space-y-1">
                                    <p>{report.hospital}</p>
                                    <p className="text-xs">{report.date}</p>
                                  </div>
                                </div>
                                <Button variant="ghost" size="sm">查看详情</Button>
                              </div>
                            </CardContent>
                          </Card>
                        ))}
                      </TabsContent>

                      {/* 亲友的过敏史 */}
                      <TabsContent value="allergies" className="space-y-3">
                        <div className="flex items-center gap-2 mb-2">
                          <AlertCircle className="w-4 h-4 text-red-600" />
                          <h4 className="text-sm">过敏史记录</h4>
                        </div>
                        <div className="text-center text-gray-500 py-8">
                          <AlertCircle className="w-12 h-12 mx-auto mb-2 opacity-30" />
                          <p>暂无过敏史记录</p>
                        </div>
                      </TabsContent>

                      {/* 亲友的过往病史 */}
                      <TabsContent value="history" className="space-y-3">
                        <div className="flex items-center gap-2 mb-2">
                          <ClipboardList className="w-4 h-4 text-blue-600" />
                          <h4 className="text-sm">过往病史</h4>
                        </div>
                        {medicalHistory.slice(2, 3).map((record) => (
                          <Card key={record.id} className="bg-gray-50">
                            <CardContent className="p-4">
                              <div className="flex items-start justify-between">
                                <div className="flex-1">
                                  <h4 className="mb-1">{record.disease}</h4>
                                  <div className="text-sm text-gray-600 space-y-1">
                                    <p>确诊时间：{record.diagnosedDate}</p>
                                    <p>{record.hospital} · {record.doctor}</p>
                                  </div>
                                </div>
                                <span className="px-2 py-1 text-xs rounded bg-green-100 text-green-700">
                                  {record.status}
                                </span>
                              </div>
                            </CardContent>
                          </Card>
                        ))}
                      </TabsContent>
                    </Tabs>
                  </DialogContent>
                </Dialog>
              </div>
            ))}
          </div>
          <p className="text-xs text-gray-500 mt-2">
            * 点击"查看"可查看亲友的所有健康信息
          </p>
        </CardContent>
      </Card>

      {/* 设置选项 */}
      <Card>
        <CardContent className="pt-6 space-y-2">
          <Button variant="ghost" className="w-full justify-start">
            <Bell className="w-4 h-4 mr-2" />
            消息提醒设置
          </Button>
          <Button variant="ghost" className="w-full justify-start">
            <Phone className="w-4 h-4 mr-2" />
            联系客服
          </Button>
          <Button variant="ghost" className="w-full justify-start">
            <Settings className="w-4 h-4 mr-2" />
            系统设置
          </Button>
        </CardContent>
      </Card>
    </div>
  );
}