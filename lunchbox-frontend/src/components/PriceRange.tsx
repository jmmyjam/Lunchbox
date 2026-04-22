interface PriceRangeProps {
  range: number
}

export default function PriceRange({ range }: PriceRangeProps) {
  return (
    <span className="price-range">
      {'$'.repeat(range)}
      <span className="price-dim">{'$'.repeat(3 - range)}</span>
    </span>
  )
}
