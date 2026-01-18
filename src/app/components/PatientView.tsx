import { useState } from "react";
import { PatientHome } from "./patient/PatientHome";
import { PatientHealth } from "./patient/PatientHealth";
import { PatientMessages } from "./patient/PatientMessages";
import { PatientChronicDisease } from "./patient/PatientChronicDisease";
import { PatientShop } from "./patient/PatientShop";
import { PatientProfile } from "./patient/PatientProfile";
import { House, Activity, MessageSquare, Heart, ShoppingBag, User } from "lucide-react";

type TabType = "home" | "health" | "messages" | "chronic" | "shop" | "profile";

export function PatientView() {
  const [activeTab, setActiveTab] = useState<TabType>("home");

  const tabs = [
    { id: "home" as TabType, label: "首页", icon: House },
    { id: "health" as TabType, label: "健康", icon: Activity },
    { id: "messages" as TabType, label: "消息", icon: MessageSquare },
    { id: "chronic" as TabType, label: "慢性病管理", icon: Heart },
    { id: "shop" as TabType, label: "商店", icon: ShoppingBag },
    { id: "profile" as TabType, label: "我的", icon: User },
  ];

  return (
    <div className="flex flex-col h-screen bg-gray-50">
      {/* 主内容区域 */}
      <div className="flex-1 overflow-auto pb-20">
        {activeTab === "home" && <PatientHome />}
        {activeTab === "health" && <PatientHealth />}
        {activeTab === "messages" && <PatientMessages />}
        {activeTab === "chronic" && <PatientChronicDisease />}
        {activeTab === "shop" && <PatientShop />}
        {activeTab === "profile" && <PatientProfile />}
      </div>

      {/* 底部导航栏 */}
      <nav className="fixed bottom-0 left-0 right-0 bg-white border-t border-gray-200 shadow-lg">
        <div className="flex justify-around items-center h-16">
          {tabs.map((tab) => {
            const Icon = tab.icon;
            return (
              <button
                key={tab.id}
                onClick={() => setActiveTab(tab.id)}
                className={`flex flex-col items-center justify-center flex-1 h-full transition-colors ${
                  activeTab === tab.id ? "text-blue-600" : "text-gray-600"
                }`}
              >
                <Icon className="w-5 h-5 mb-1" />
                <span className="text-xs">{tab.label}</span>
              </button>
            );
          })}
        </div>
      </nav>
    </div>
  );
}
