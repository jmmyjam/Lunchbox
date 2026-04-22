import type { Order, View } from '../types'

interface HeaderProps {
  view: View
  orders: Order[]
  onNavigate: (v: View) => void
}

export default function Header({ view, orders, onNavigate }: HeaderProps) {
  const inProgressCount = orders.filter(o => o.status === 'IN_PROGRESS').length

  return (
    <header>
      <div className="header-inner">
        <div className="logo">🥡 Lunchbox</div>
        <nav>
          <button className={view === 'home' ? 'active' : ''} onClick={() => onNavigate('home')}>
            Restaurants
          </button>
          <button className={view === 'orders' ? 'active' : ''} onClick={() => onNavigate('orders')}>
            My Orders {inProgressCount > 0 && <span className="badge">{inProgressCount}</span>}
          </button>
        </nav>
      </div>
    </header>
  )
}
