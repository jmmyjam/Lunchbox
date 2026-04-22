import { useEffect, useState } from 'react'
import type { Business, Order, View } from '../types'

export function useOrders(view: View) {
  const [orders, setOrders] = useState<Order[]>([])
  const [error, setError] = useState('')

  useEffect(() => {
    if (view === 'orders') {
      fetch('http://localhost:8080/orders')
        .then(r => r.json())
        .then(data => {
          const list = data._embedded?.orderList ?? data
          setOrders(list)
        })
        .catch(() => setError('Failed to load orders.'))
    }
  }, [view])

  function placeOrder(business: Business, description: string) {
    fetch('http://localhost:8080/orders', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ description: `[${business.name}] ${description}` }),
    })
      .then(r => r.json())
      .then(order => setOrders(prev => [order, ...prev]))
      .catch(() => setError('Failed to place order.'))
  }

  function cancelOrder(id: number) {
    fetch(`http://localhost:8080/orders/${id}/cancel`, { method: 'DELETE' })
      .then(r => r.json())
      .then(updated => setOrders(prev => prev.map(o => o.id === id ? updated : o)))
      .catch(() => setError('Failed to cancel order.'))
  }

  function completeOrder(id: number) {
    fetch(`http://localhost:8080/orders/${id}/complete`, { method: 'PUT' })
      .then(r => r.json())
      .then(updated => setOrders(prev => prev.map(o => o.id === id ? updated : o)))
      .catch(() => setError('Failed to complete order.'))
  }

  return { orders, error, setError, placeOrder, cancelOrder, completeOrder }
}
