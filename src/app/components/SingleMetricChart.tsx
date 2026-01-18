import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, ReferenceLine } from "recharts";

interface SingleMetricChartProps {
  title: string;
  data: Array<{ date: string; value: number }>;
  normalRange: { min: number; max: number };
  unit: string;
  color: string;
}

export function SingleMetricChart({ title, data, normalRange, unit, color }: SingleMetricChartProps) {
  return (
    <div className="w-full bg-white rounded-lg p-4 shadow">
      <h4 className="mb-3">{title}</h4>
      <div className="h-48">
        <ResponsiveContainer width="100%" height="100%">
          <LineChart data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" tick={{ fontSize: 12 }} />
            <YAxis tick={{ fontSize: 12 }} />
            <Tooltip 
              formatter={(value: number) => [`${value} ${unit}`, title]}
            />
            {/* 正常范围参考线 - 用绿色标注 */}
            <ReferenceLine 
              y={normalRange.min} 
              stroke="#22c55e" 
              strokeDasharray="3 3" 
              label={{ value: `正常下限: ${normalRange.min}`, position: 'left', fontSize: 10, fill: '#22c55e' }}
            />
            <ReferenceLine 
              y={normalRange.max} 
              stroke="#22c55e" 
              strokeDasharray="3 3" 
              label={{ value: `正常上限: ${normalRange.max}`, position: 'left', fontSize: 10, fill: '#22c55e' }}
            />
            <Line 
              type="monotone" 
              dataKey="value" 
              stroke={color} 
              strokeWidth={2} 
              dot={{ fill: color }}
            />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}
