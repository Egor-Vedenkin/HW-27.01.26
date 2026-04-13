package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProductToBasket_ProductDoesNotExist_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> basketService.addProductToBasket(id));
        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void addProductToBasket_ProductExists_CallsAddProduct() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        basketService.addProductToBasket(id);
        verify(productBasket).addProduct(id);
    }

    @Test
    void getUserBasket_ProductBasketEmpty_ReturnsEmptyUserBasket() {
        when(productBasket.getItems()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0, userBasket.getTotal());
    }
}