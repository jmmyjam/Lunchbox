import { useParams, useNavigate } from 'react-router-dom'
import type { Business } from '../types'
import { useMenu } from '../hooks/useMenu'

interface RestaurantPageProps {
  businesses: Business[]
  onOrder: (business: Business, desc: string) => void
}

export default function RestaurantPage({ businesses, onOrder }: RestaurantPageProps) {
  const { id } = useParams<{ id: string }>()
  const navigate = useNavigate()
  const business = businesses.find(b => b.id === Number(id))
  const { items, loading, error } = useMenu(Number(id))

  if (!business) {
    return (
      <div className="page-header" style={{ textAlign: 'center' }}>
        <p className="empty">Restaurant not found.</p>
      </div>
    )
  }

  const categories = [...new Set(items.map(i => i.category))]

  return (
    <div>
      {/* Hero banner */}
      <div className="restaurant-hero">
        <button className="back-btn" onClick={() => navigate('/')}>
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <polyline points="15 18 9 12 15 6"/>
          </svg>
          Back
        </button>
        <div className="restaurant-hero-info">
          <h1>{business.name}</h1>
          <div className="restaurant-meta">
            <span>⭐ {business.rating.toFixed(1)}</span>
            <span className="dot">·</span>
            <span>🕐 {business.avgPrepTimeMinutes}–{business.avgPrepTimeMinutes + 10} min</span>
            <span className="dot">·</span>
            <span className="free-delivery">Free delivery</span>
            <span className="dot">·</span>
            <span className="type-chip">{business.type}</span>
          </div>
          <p className="restaurant-desc">{business.description}</p>
        </div>
      </div>

      {/* Menu */}
      <div className="menu-container">
        {error && <p className="empty">{error}</p>}
        {loading && <p className="loading">Loading menu...</p>}

        {!loading && categories.map(category => (
          <div key={category} className="menu-section">
            <h2 className="menu-category">{category}</h2>
            <div className="menu-items">
              {items.filter(i => i.category === category).map(item => (
                <div key={item.id} className="menu-item">
                  <div className="menu-item-info">
                    <span className="menu-item-name">{item.name}</span>
                    <span className="menu-item-desc">{item.description}</span>
                  </div>
                  <div className="menu-item-right">
                    <span className="menu-item-price">${item.price.toFixed(2)}</span>
                    <button
                      className="add-btn"
                      onClick={() => onOrder(business, item.name)}
                    >
                      +
                    </button>
                  </div>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
