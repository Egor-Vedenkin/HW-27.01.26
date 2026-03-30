package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket; // Корзина товаров.

    private final StorageService storageService; // Для получения цен товаров.

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }
}