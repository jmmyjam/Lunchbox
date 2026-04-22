export interface Business {
  id: number
  name: string
  type: string
  address: string
  phoneNumber: string
  email: string
  description: string
  priceRange: number
  isActive: boolean
  rating: number
  avgPrepTimeMinutes: number
}

export interface Order {
  id: number
  description: string
  status: 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED'
}

export type View = 'home' | 'orders'
