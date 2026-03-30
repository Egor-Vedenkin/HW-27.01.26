package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import java.util.UUID;

public class BasketItem {
    private final UUID productId;
    private final int quantity;

    // Для удобства — можно добавить ссылку на продукт, если нужно в будущем.

    public BasketItem(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity > 0 ? quantity : 1; // Минимум 1 штука в корзине.
    }

    public UUID getProductId() { return productId; }
    public int getQuantity() { return quantity; }
}