import type { Business } from '../types'
import PriceRange from './PriceRange'

interface BusinessCardProps {
  business: Business
  onOrder: (b: Business) => void
}

export default function BusinessCard({ business, onOrder }: BusinessCardProps) {
  return (
    <div className="card">
      <div className="card-header">
        <span className="type-badge">{business.type}</span>
        <PriceRange range={business.priceRange} />
      </div>
      <h3>{business.name}</h3>
      <p className="description">{business.description}</p>
      <p className="address">📍 {business.address}</p>
      <button className="order-btn" onClick={() => onOrder(business)}>
        Order Now
      </button>
    </div>
  )
}
