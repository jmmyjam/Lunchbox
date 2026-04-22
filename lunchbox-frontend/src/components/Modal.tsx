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
        <button className="modal-close" onClick={onClose}>✕</button>
        <div className="modal-header">
          <h2>{business.name}</h2>
          <p className="modal-sub">{business.address}</p>
        </div>
        <div className="modal-body">
          <label className="modal-label">What would you like to order?</label>
          <textarea
            value={desc}
            onChange={e => setDesc(e.target.value)}
            placeholder="e.g. 2x Burger Combo, 1x Large Fries..."
            rows={4}
            autoFocus
          />
        </div>
        <div className="modal-footer">
          <button
            className="place-order-btn"
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
