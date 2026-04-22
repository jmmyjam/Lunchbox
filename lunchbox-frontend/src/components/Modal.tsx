import { useState } from 'react'
import type { Business } from '../types'

interface ModalProps {
  business: Business
  onClose: () => void
  onSubmit: (desc: string) => void
}

export default function Modal({ business, onClose, onSubmit }: ModalProps) {
  const [desc, setDesc] = useState('')

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={e => e.stopPropagation()}>
        <h2>Order from {business.name}</h2>
        <p className="modal-sub">What would you like to order?</p>
        <textarea
          value={desc}
          onChange={e => setDesc(e.target.value)}
          placeholder="e.g. 2x Burger Combo, 1x Large Fries..."
          rows={4}
        />
        <div className="modal-actions">
          <button className="cancel-btn" onClick={onClose}>Cancel</button>
          <button
            className="order-btn"
            disabled={!desc.trim()}
            onClick={() => { onSubmit(desc); onClose() }}
          >
            Place Order
          </button>
        </div>
      </div>
    </div>
  )
}
