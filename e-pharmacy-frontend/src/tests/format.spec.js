import { test, expect } from 'vitest';
import { formatPrice } from '@/scripts/Format';

test('test formatPrice function integer', () => {
    expect(formatPrice(16)).toBe('16.00 DH');
});

test('test formatPrice function float', () => {
    expect(formatPrice(2.22222)).toBe('2.22 DH');
});