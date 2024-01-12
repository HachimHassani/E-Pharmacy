import { test, expect } from 'vitest';
import { getOrdonnancePrice } from '@/scripts/Ordonnance';


test('test get ordonnance price function', () => {
    const ordonnance = {
        "ordonnanceId": 152178,
        "nomMedecin": "Dr. Ksiks",
        "nomPatient": "Mr. Ksiks Mohamed Haitam",
        "date": "11-01-2024",
        "lieu": "Rabat",
        "medicaments": [
            {
                "brandName": "Doliprane",
                "medicationName": "Paracetamol",
                "price": 14.99
            },
            {
                "brandName": "Tussidane",
                "medicationName": "1,5 mg/ml sans sucre",
                "price": 24.99
            }
        ]
    };

    expect(getOrdonnancePrice(ordonnance)).toBe(39.98);
});

