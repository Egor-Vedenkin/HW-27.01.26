package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductBasket {
    // Приватное финальное поле с картой товаров (id -> количество)
    private final Map<UUID, Integer> items = new HashMap<>();

    public void addProduct(UUID id) {
        items.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}