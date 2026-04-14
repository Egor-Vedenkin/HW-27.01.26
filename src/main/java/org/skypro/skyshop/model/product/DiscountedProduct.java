package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int discountPercent;
    private final int basePrice; // Сделано final для неизменности цены после создания
    private final int salePrice; // Сделано final

    public DiscountedProduct(UUID id, String title, int price, int discountPercent) {
        super(id, title);
        if (price <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше нуля");
        }
        // Исправлено условие: добавлено &&
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть от 0 до 100 включительно");
        }
        this.basePrice = price;
        this.discountPercent = discountPercent;

        // Расчет финальной цены при создании объекта с использованием переданного процента
        this.salePrice = calculateSalePrice(price, discountPercent);
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
        // Расчет фактического процента скидки для вывода (на случай, если расчет в конструкторе изменится)
        double actualDiscountPercentage = ((double) (basePrice - salePrice)) / basePrice * 100;
        return getTitle() + ": " + salePrice + " (" + Math.round(actualDiscountPercentage) + "%)";
    }

    // Вспомогательный метод для расчета цены со скидкой
    private static int calculateSalePrice(int basePrice, int discountPercent) {
        return (int) (basePrice * (100 - discountPercent) / 100.0);
    }
}