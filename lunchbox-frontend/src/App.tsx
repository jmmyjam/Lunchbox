import { useState } from 'react'
import './App.css'
import type { View, Business } from './types'
import { useBusinesses } from './hooks/useBusinesses'
import { useOrders } from './hooks/useOrders'
import Header from './components/Header'
import BusinessCard from './components/BusinessCard'
import OrderCard from './components/OrderCard'
import Modal from './components/Modal'

export default function App() {
  const [view, setView] = useState<View>('home')
  const [search, setSearch] = useState('')
  const [selectedBusiness, setSelectedBusiness] = useState<Business | null>(null)

  const { businesses, loading, error: bizError, setError: setBizError } = useBusinesses()
  const { orders, error: orderError, setError: setOrderError, placeOrder, cancelOrder, completeOrder } = useOrders(view)

  const error = bizError || orderError
  const clearError = () => { setBizError(''); setOrderError('') }

  const filtered = businesses.filter(b =>
    b.name.toLowerCase().includes(search.toLowerCase()) ||
    b.type.toLowerCase().includes(search.toLowerCase())
  )

  function handleOrder(business: Business, desc: string) {
    placeOrder(business, desc)
    setView('orders')
  }

  return (
    <div className="app">
      <Header view={view} orders={orders} onNavigate={setView} />

      <main>
        {error && (
          <div className="error-banner">
            {error} <button onClick={clearError}>✕</button>
          </div>
        )}

        {view === 'home' && (
          <>
            <div className="hero-section">
              <h1>Hungry? We've got you.</h1>
              <p>Order from local restaurants and businesses near you.</p>
              <input
                className="search"
                type="text"
                placeholder="Search restaurants or cuisine..."
                value={search}
                onChange={e => setSearch(e.target.value)}
              />
            </div>

            {loading && <div className="loading">Loading restaurants...</div>}

            <div className="grid">
              {filtered.map(b => (
                <BusinessCard key={b.id} business={b} onOrder={setSelectedBusiness} />
              ))}
              {!loading && filtered.length === 0 && (
                <p className="empty">No restaurants found.</p>
              )}
            </div>
          </>
        )}

        {view === 'orders' && (
          <>
            <div className="page-header">
              <h2>My Orders</h2>
            </div>
            <div className="orders-list">
              {orders.length === 0 && <p className="empty">No orders yet. Go order something!</p>}
              {orders.map(o => (
                <OrderCard key={o.id} order={o} onCancel={cancelOrder} onComplete={completeOrder} />
              ))}
            </div>
          </>
        )}
      </main>

      {selectedBusiness && (
        <Modal
          business={selectedBusiness}
          onClose={() => setSelectedBusiness(null)}
          onSubmit={desc => handleOrder(selectedBusiness, desc)}
        />
      )}
    </div>
  )
}
