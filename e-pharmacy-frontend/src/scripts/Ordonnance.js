// calculate ordonnance price
export function getOrdonnancePrice(ordonnance) {
    let total = 0;
    
    ordonnance.medicaments.forEach((med) => {
        total += med.price;
    });

    return total;
}