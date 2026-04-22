import type { Order } from '../types'

const STATUS_CONFIG = {
  IN_PROGRESS: { label: 'In Progress', icon: '🛵', color: '#ff6b35' },
  COMPLETED: { label: 'Delivered', icon: '✅', color: '#2e7d32' },
  CANCELLED: { label: 'Cancelled', icon: '✕', color: '#b71c1c' },
}

interface OrderCardProps {
  order: Order
  onCancel: (id: number) => void
  onComplete: (id: number) => void
}

export default function OrderCard({ order, onCancel, onComplete }: OrderCardProps) {
  const config = STATUS_CONFIG[order.status]

  return (
    <div className="order-card">
      <div className="order-icon" style={{ background: config.color + '18', color: config.color }}>
        {config.icon}
      </div>
      <div className="order-info">
        <span className="order-desc">{order.description}</span>
        <span className="order-status" style={{ color: config.color }}>{config.label}</span>
      </div>
      {order.status === 'IN_PROGRESS' && (
        <div className="order-actions">
          <button className="complete-btn" onClick={() => onComplete(order.id)}>Mark Delivered</button>
          <button className="cancel-btn" onClick={() => onCancel(order.id)}>Cancel</button>
        </div>
      )}
    </div>
  )
}
