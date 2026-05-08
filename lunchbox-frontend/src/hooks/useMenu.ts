import { useState, useEffect } from 'react'
import type { MenuItem } from '../types'

export function useMenu(businessId: number) {
  const [items, setItems] = useState<MenuItem[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    setLoading(true)
    fetch(`http://localhost:8080/businesses/${businessId}/menu`)
      .then(r => r.json())
      .then((data: MenuItem[]) => { setItems(data); setLoading(false) })
      .catch(() => { setError('Failed to load menu.'); setLoading(false) })
  }, [businessId])

  return { items, loading, error }
}
