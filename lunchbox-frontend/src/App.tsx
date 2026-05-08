import { useState } from 'react'
import { Routes, Route, useNavigate } from 'react-router-dom'
import './App.css'
import type { Business } from './types'
import { useBusinesses } from './hooks/useBusinesses'
import { useOrders } from './hooks/useOrders'
import Header from './components/Header'
import BusinessCard from './components/BusinessCard'
import OrderCard from './components/OrderCard'
import RestaurantPage from './components/RestaurantPage'

type View = 'home' | 'orders'

export default function App() {
  const [view, setView] = useState<View>('home')
  const [search, setSearch] = useState('')
  const navigate = useNavigate()

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
    navigate('/')
  }

  function handleNavigate(v: View) {
    setView(v)
    navigate('/')
  }

  return (
    <div className="app">
      <Header view={view} orders={orders} onNavigate={handleNavigate} />

      <main>
        {error && (
          <div className="error-banner">
            {error} <button onClick={clearError}>✕</button>
          </div>
        )}

        <Routes>
          <Route path="/restaurant/:id" element={
            <RestaurantPage businesses={businesses} onOrder={handleOrder} />
          } />

          <Route path="/" element={
            view === 'orders' ? (
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
            ) : (
              <>
                <div className="hero-section">
                  <h1>Hungry? We've got you.</h1>
                  <p>Order from local restaurants and businesses near you.</p>
                  <div className="search-bar">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/></svg>
                    <input
                      type="text"
                      placeholder="Search restaurants or cuisine..."
                      value={search}
                      onChange={e => setSearch(e.target.value)}
                    />
                  </div>
                </div>

                {loading && <div className="loading">Loading restaurants...</div>}
                {!loading && <p className="section-title">All Restaurants</p>}

                <div className="grid">
                  {filtered.map(b => (
                    <BusinessCard
                      key={b.id}
                      business={b}
                      onOrder={() => navigate(`/restaurant/${b.id}`)}
                    />
                  ))}
                  {!loading && filtered.length === 0 && (
                    <p className="empty">No restaurants found.</p>
                  )}
                </div>
              </>
            )
          } />
        </Routes>
      </main>
    </div>
  )
}
