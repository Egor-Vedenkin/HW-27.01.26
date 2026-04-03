package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> items;
    private final int total;

    // Конструктор: принимает только items, total считается через отдельный метод
    private UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = calculateTotal(items);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    // Статический метод подсчёта общей стоимости — принимает список элементов
    private static int calculateTotal(List<BasketItem> items) {
        return items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Фабричный метод для создания корзины из списка — единственный публичный способ создания
    public static UserBasket fromItems(List<BasketItem> items) {
        return new UserBasket(items);
    }
}