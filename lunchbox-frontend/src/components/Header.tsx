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
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/></svg>
            Home
          </button>
          <button className={view === 'orders' ? 'active' : ''} onClick={() => onNavigate('orders')}>
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M9 5H7a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-2"/><rect x="9" y="3" width="6" height="4" rx="1"/></svg>
            Orders
            {inProgressCount > 0 && <span className="badge">{inProgressCount}</span>}
          </button>
        </nav>
      </div>
    </header>
  )
}
