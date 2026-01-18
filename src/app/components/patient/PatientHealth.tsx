import { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Button } from "../ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "../ui/dialog";
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import { Sparkles, FileText, Stethoscope, Pill, BookOpen, Camera, Upload } from "lucide-react";

export function PatientHealth() {
  const [reportDialogOpen, setReportDialogOpen] = useState(false);

  const healthModules = [
    {
      title: "AI报告解读",
      icon: Sparkles,
      color: "bg-gradient-to-br from-purple-50 to-pink-50 text-purple-600 border-purple-200",
      description: "智能分析您的体检报告，提供专业的健康建议",
      reports: [
        { 
          date: "2024-12-15", 
          type: "血常规+血脂检查", 
          summary: "检测到血脂异常，建议调整饮食",
          aiScore: 75
        },
        { 
          date: "2024-11-20", 
          type: "全面体检", 
          summary: "整体健康状况良好，需注意血压控制",
          aiScore: 82
        },
      ],
    },
    {
      title: "我的病历",
      icon: FileText,
      color: "bg-green-50 text-green-600 border-green-200",
      description: "需要人脸识别后查看",
    },
    {
      title: "线上问诊",
      icon: Stethoscope,
      color: "bg-purple-50 text-purple-600 border-purple-200",
      description: "在线咨询专业医生",
    },
    {
      title: "医嘱",
      icon: Pill,
      color: "bg-red-50 text-red-600 border-red-200",
      description: "查看医生开具的医嘱和用药计划",
    },
    {
      title: "健康课程",
      icon: BookOpen,
      color: "bg-yellow-50 text-yellow-600 border-yellow-200",
      description: "学习健康知识",
    },
  ];

  return (
    <div className="p-4 space-y-4">
      {/* 我的报告 - 置顶显示 */}
      <Card className="border-2 border-indigo-200 bg-indigo-50">
        <CardHeader>
          <CardTitle className="flex items-center gap-2 text-indigo-600">
            <FileText className="w-5 h-5" />
            我的报告
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-3">
          <p className="text-sm text-gray-600">上传或录入您的体检报告</p>
          <div className="flex gap-2">
            <Dialog open={reportDialogOpen} onOpenChange={setReportDialogOpen}>
              <DialogTrigger asChild>
                <Button variant="outline" className="flex-1">
                  <Upload className="w-4 h-4 mr-2" />
                  手动输入
                </Button>
              </DialogTrigger>
              <DialogContent className="max-w-md">
                <DialogHeader>
                  <DialogTitle>手动输入体检数据</DialogTitle>
                </DialogHeader>
                <div className="space-y-4">
                  <div>
                    <Label>体检项目</Label>
                    <Input placeholder="例如：血常规、尿常规" />
                  </div>
                  <div>
                    <Label>检查日期</Label>
                    <Input type="date" />
                  </div>
                  <div>
                    <Label>检查结果</Label>
                    <Input placeholder="输入检查结果" />
                  </div>
                  <Button className="w-full">保存</Button>
                </div>
              </DialogContent>
            </Dialog>
            <Button variant="outline" className="flex-1">
              <Camera className="w-4 h-4 mr-2" />
              拍照识别
            </Button>
            <Button variant="outline" className="flex-1">
              <Upload className="w-4 h-4 mr-2" />
              文件上传
            </Button>
          </div>
          {/* 已上传的报告列表 */}
          <div className="space-y-2 mt-4">
            <h4 className="text-sm">最近报告</h4>
            <div className="space-y-2">
              <div className="bg-white p-3 rounded-lg border">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm">血常规检查</p>
                    <p className="text-xs text-gray-500">2024-12-15</p>
                  </div>
                  <Button variant="ghost" size="sm">查看</Button>
                </div>
              </div>
              <div className="bg-white p-3 rounded-lg border">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm">血脂检查</p>
                    <p className="text-xs text-gray-500">2024-12-15</p>
                  </div>
                  <Button variant="ghost" size="sm">查看</Button>
                </div>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>

      {/* 其他健康模块 */}
      {healthModules.map((module, idx) => {
        const Icon = module.icon;
        return (
          <Card key={idx} className={`border ${module.color}`}>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Icon className="w-5 h-5" />
                {module.title}
              </CardTitle>
            </CardHeader>
            <CardContent>
              {module.reports ? (
                <div className="space-y-3">
                  <p className="text-sm text-gray-600">{module.description}</p>
                  <div className="space-y-2">
                    {module.reports.map((report, reportIdx) => (
                      <div key={reportIdx} className="bg-white p-4 rounded-lg border">
                        <div className="flex items-start justify-between mb-2">
                          <div className="flex-1">
                            <p className="text-sm">{report.type}</p>
                            <p className="text-xs text-gray-500">{report.date}</p>
                          </div>
                          <div className="text-right">
                            <div className="text-xs text-gray-500">AI健康评分</div>
                            <div className={`text-lg ${report.aiScore >= 80 ? 'text-green-600' : report.aiScore >= 60 ? 'text-yellow-600' : 'text-red-600'}`}>
                              {report.aiScore}
                            </div>
                          </div>
                        </div>
                        <div className="bg-purple-50 p-2 rounded text-sm text-gray-700">
                          <strong>AI解读：</strong>{report.summary}
                        </div>
                        <Button variant="ghost" size="sm" className="w-full mt-2">
                          查看详细解读
                        </Button>
                      </div>
                    ))}
                  </div>
                </div>
              ) : (
                <>
                  <p className="text-sm text-gray-600">{module.description}</p>
                  <Button variant="outline" className="w-full mt-3">
                    查看详情
                  </Button>
                </>
              )}
            </CardContent>
          </Card>
        );
      })}
    </div>
  );
}