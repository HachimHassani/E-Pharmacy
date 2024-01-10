
export function formatPrice(value) {
    return `${(Math.round(value * 100) / 100).toFixed(2)} DH`;
}
