package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int discountPercent;
    private int basePrice;
    private int salePrice;

    public DiscountedProduct(UUID id, String title, int price, int discountPercent) {
        super(id, title);
        if (price <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше нуля");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть от 0 до 100 включительно");
        }
        this.basePrice = price;
        this.discountPercent = discountPercent;
        calculateSalePrice(); // Расчёт финальной цены при создании объекта
    }

    private void calculateSalePrice() {
        salePrice = (int) (basePrice * (100 - discountPercent) / 100.0);
    }

    @Override
    public int getPrice() {
        return salePrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        double actualDiscountPercentage = ((double) (basePrice - salePrice)) / basePrice * 100;
        return getTitle() + ": " + salePrice + " (" + Math.round(actualDiscountPercentage) + "%)";
    }
}