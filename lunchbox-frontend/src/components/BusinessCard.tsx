import type { Business } from '../types'

const TYPE_EMOJI: Record<string, string> = {
  restaurant: '🍔',
  pizza: '🍕',
  sushi: '🍣',
  chinese: '🥡',
  mexican: '🌮',
  italian: '🍝',
  retail: '🛍️',
  cafe: '☕',
  dessert: '🍦',
}

const TYPE_COLOR: Record<string, string> = {
  restaurant: '#ff6b35',
  pizza: '#e63946',
  sushi: '#2196f3',
  chinese: '#ff9800',
  mexican: '#4caf50',
  italian: '#9c27b0',
  retail: '#607d8b',
  cafe: '#795548',
  dessert: '#e91e63',
}

function getBgColor(type: string) {
  return TYPE_COLOR[type.toLowerCase()] ?? '#e85d04'
}

function getEmoji(type: string) {
  return TYPE_EMOJI[type.toLowerCase()] ?? '🍽️'
}

interface BusinessCardProps {
  business: Business
  onOrder: (b: Business) => void
}

export default function BusinessCard({ business, onOrder }: BusinessCardProps) {
  const bg = getBgColor(business.type)
  const emoji = getEmoji(business.type)
  const deliveryTime = 15 + (business.id % 3) * 10

  return (
    <div className="card" onClick={() => onOrder(business)}>
      <div className="card-image" style={{ background: `linear-gradient(135deg, ${bg}cc, ${bg}88)` }}>
        <span className="card-emoji">{emoji}</span>
        <span className="card-delivery-badge">🕐 {deliveryTime}–{deliveryTime + 10} min</span>
      </div>
      <div className="card-body">
        <div className="card-top">
          <h3>{business.name}</h3>
          <span className="card-rating">⭐ {(4 + (business.id % 10) / 10).toFixed(1)}</span>
        </div>
        <div className="card-meta">
          <span className="type-chip">{business.type}</span>
          <span className="dot">·</span>
          <span dangerouslySetInnerHTML={{ __html: '$'.repeat(business.priceRange) + `<span class="price-dim">${'$'.repeat(3 - business.priceRange)}</span>` }} />
          <span className="dot">·</span>
          <span className="free-delivery">Free delivery</span>
        </div>
        <p className="card-desc">{business.description}</p>
      </div>
    </div>
  )
}
