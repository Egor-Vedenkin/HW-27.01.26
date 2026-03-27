package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> productOpt = storageService.getProductById(id);
        if (productOpt.isEmpty()) {
            throw new IllegalArgumentException("Товар с id " + id + " не найден");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        return UserBasket.fromProductBasket(productBasket, storageService);
    }
}