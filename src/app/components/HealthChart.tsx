import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Legend } from "recharts";

interface HealthChartProps {
  data: Array<{ 
    date: string; 
    bloodSugar?: number; // 血糖 mmol/L
    bloodPressure?: number; // 收缩压 mmHg
    cholesterol?: number; // 总胆固醇 mmol/L
  }>;
}

export function HealthChart({ data }: HealthChartProps) {
  return (
    <div className="w-full h-80 bg-white rounded-lg p-4 shadow">
      <h3 className="mb-4">健康指标变化趋势</h3>
      <ResponsiveContainer width="100%" height="100%">
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="date" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line 
            type="monotone" 
            dataKey="bloodSugar" 
            stroke="#ef4444" 
            strokeWidth={2} 
            name="血糖 (mmol/L)"
            dot={{ fill: '#ef4444' }}
          />
          <Line 
            type="monotone" 
            dataKey="bloodPressure" 
            stroke="#3b82f6" 
            strokeWidth={2} 
            name="收缩压 (mmHg)"
            dot={{ fill: '#3b82f6' }}
          />
          <Line 
            type="monotone" 
            dataKey="cholesterol" 
            stroke="#f59e0b" 
            strokeWidth={2} 
            name="总胆固醇 (mmol/L)"
            dot={{ fill: '#f59e0b' }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}