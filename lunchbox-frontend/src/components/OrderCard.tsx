import type { Order } from '../types'

interface OrderCardProps {
  order: Order
  onCancel: (id: number) => void
  onComplete: (id: number) => void
}

export default function OrderCard({ order, onCancel, onComplete }: OrderCardProps) {
  const statusClass = order.status.toLowerCase().replace('_', '-')

  return (
    <div className={`order-card status-${statusClass}`}>
      <div className="order-info">
        <span className="order-desc">{order.description}</span>
        <span className={`status-badge ${statusClass}`}>{order.status.replace('_', ' ')}</span>
      </div>
      {order.status === 'IN_PROGRESS' && (
        <div className="order-actions">
          <button className="complete-btn" onClick={() => onComplete(order.id)}>Complete</button>
          <button className="cancel-btn" onClick={() => onCancel(order.id)}>Cancel</button>
        </div>
      )}
    </div>
  )
}
