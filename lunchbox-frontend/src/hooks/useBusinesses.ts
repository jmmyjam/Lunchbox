import { useEffect, useState } from 'react'
import type { Business } from '../types'

export function useBusinesses() {
  const [businesses, setBusinesses] = useState<Business[]>([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  useEffect(() => {
    setLoading(true)
    fetch('http://localhost:8080/businesses')
      .then(r => r.json())
      .then(data => {
        const list = data._embedded?.businessList ?? data
        setBusinesses(list.filter((b: Business) => b.isActive))
      })
      .catch(() => setError('Could not connect to server. Is the backend running?'))
      .finally(() => setLoading(false))
  }, [])

  return { businesses, loading, error, setError }
}
