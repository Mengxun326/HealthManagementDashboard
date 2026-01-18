import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Button } from "../ui/button";
import { Bell, Pill, Calendar, Clock } from "lucide-react";

const medicationReminders = [
  {
    id: 1,
    medicine: "降压药（缬沙坦胶囊）",
    dosage: "80mg",
    frequency: "每日1次",
    time: "早餐后",
    prescribedBy: "李医生",
    prescribedDate: "2024-12-10",
    nextDose: "今日 08:00",
    status: "待服用",
  },
  {
    id: 2,
    medicine: "他汀类药物（阿托伐他汀）",
    dosage: "20mg",
    frequency: "每日1次",
    time: "晚餐后",
    prescribedBy: "李医生",
    prescribedDate: "2024-12-10",
    nextDose: "今日 19:00",
    status: "待服用",
  },
  {
    id: 3,
    medicine: "维生素D3",
    dosage: "1粒",
    frequency: "每日1次",
    time: "早餐后",
    prescribedBy: "张医生",
    prescribedDate: "2024-11-20",
    nextDose: "今日 08:00",
    status: "已服用",
  },
];

const followUpReminders = [
  {
    id: 1,
    hospital: "市人民医院",
    department: "心内科",
    doctor: "李医生",
    date: "2024-12-25",
    time: "14:00",
    reason: "高血压复查",
    status: "待就诊",
    prescribedDate: "2024-12-10",
  },
  {
    id: 2,
    hospital: "市人民医院",
    department: "内分泌科",
    doctor: "张医生",
    date: "2025-01-15",
    time: "09:30",
    reason: "血脂复查",
    status: "待就诊",
    prescribedDate: "2024-11-20",
  },
];

export function PatientMessages() {
  return (
    <div className="p-4 space-y-4 bg-gray-50">
      {/* 标题 */}
      <div className="flex items-center gap-2 mb-4">
        <Bell className="w-6 h-6 text-blue-600" />
        <h2 className="text-xl">提醒中心</h2>
      </div>

      {/* 用药提醒 */}
      <Card className="border-2 border-green-200 bg-green-50">
        <CardHeader>
          <CardTitle className="flex items-center gap-2 text-green-700">
            <Pill className="w-5 h-5" />
            用药提醒
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-3">
          {medicationReminders.map((reminder) => (
            <Card key={reminder.id} className="bg-white">
              <CardContent className="p-4">
                <div className="flex items-start justify-between mb-3">
                  <div className="flex-1">
                    <h4 className="text-base mb-1">{reminder.medicine}</h4>
                    <div className="text-sm text-gray-600 space-y-1">
                      <p>剂量：{reminder.dosage} · {reminder.frequency} · {reminder.time}</p>
                      <p className="text-xs">开药医生：{reminder.prescribedBy} ({reminder.prescribedDate})</p>
                    </div>
                  </div>
                  <span
                    className={`px-3 py-1 rounded-full text-xs ${
                      reminder.status === "待服用"
                        ? "bg-orange-100 text-orange-700"
                        : "bg-green-100 text-green-700"
                    }`}
                  >
                    {reminder.status}
                  </span>
                </div>
                <div className="flex items-center justify-between pt-3 border-t">
                  <div className="flex items-center gap-2 text-sm text-gray-600">
                    <Clock className="w-4 h-4" />
                    <span>下次服药：{reminder.nextDose}</span>
                  </div>
                  {reminder.status === "待服用" && (
                    <Button size="sm" variant="outline">
                      标记已服用
                    </Button>
                  )}
                </div>
              </CardContent>
            </Card>
          ))}
          <p className="text-xs text-gray-500 text-center mt-2">
            * 用药提醒由医生开药时设置
          </p>
        </CardContent>
      </Card>

      {/* 复诊提醒 */}
      <Card className="border-2 border-blue-200 bg-blue-50">
        <CardHeader>
          <CardTitle className="flex items-center gap-2 text-blue-700">
            <Calendar className="w-5 h-5" />
            复诊提醒
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-3">
          {followUpReminders.map((reminder) => (
            <Card key={reminder.id} className="bg-white">
              <CardContent className="p-4">
                <div className="flex items-start justify-between mb-3">
                  <div className="flex-1">
                    <h4 className="text-base mb-1">{reminder.reason}</h4>
                    <div className="text-sm text-gray-600 space-y-1">
                      <p>{reminder.hospital} · {reminder.department}</p>
                      <p>医生：{reminder.doctor}</p>
                      <p className="text-xs">预约设置：{reminder.prescribedDate}</p>
                    </div>
                  </div>
                  <span className="px-3 py-1 bg-blue-100 text-blue-700 rounded-full text-xs">
                    {reminder.status}
                  </span>
                </div>
                <div className="flex items-center justify-between pt-3 border-t bg-blue-50 -mx-4 -mb-4 px-4 py-3 rounded-b-lg">
                  <div className="flex items-center gap-2">
                    <Calendar className="w-4 h-4 text-blue-600" />
                    <span className="text-blue-700">
                      {reminder.date} {reminder.time}
                    </span>
                  </div>
                  <Button size="sm" variant="outline">
                    查看详情
                  </Button>
                </div>
              </CardContent>
            </Card>
          ))}
          <p className="text-xs text-gray-500 text-center mt-2">
            * 复诊提醒由医生开药时设置
          </p>
        </CardContent>
      </Card>

      {/* 提示信息 */}
      <Card className="bg-purple-50 border-purple-200">
        <CardContent className="p-4">
          <div className="flex items-start gap-3">
            <Bell className="w-5 h-5 text-purple-600 mt-1" />
            <div className="text-sm text-gray-700">
              <p className="mb-1">
                <strong>提示：</strong>所有用药提醒和复诊提醒均由医生在开药时为您设置。
              </p>
              <p className="text-xs text-gray-500">
                系统会在预定时间前提前通知您，请注意查收。
              </p>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}