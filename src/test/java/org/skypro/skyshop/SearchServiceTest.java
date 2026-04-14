package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void search_NoObjectsInStorage_ReturnsEmptyList() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());
        List<SearchResult> results = new ArrayList<>(searchService.search("java"));
        assertTrue(results.isEmpty());
    }

    @Test
    void search_ObjectsExistButNoMatch_ReturnsEmptyList() {
        Product product = mock(Product.class);
        when(product.getSearchTerm()).thenReturn("хлеб");
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        List<SearchResult> results = new ArrayList<>(searchService.search("java"));
        assertTrue(results.isEmpty());
    }

    @Test
    void search_MatchFound_ReturnsSearchResult() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(UUID.randomUUID());
        when(product.getName()).thenReturn("Java для чайников");
        when(product.getContentType()).thenReturn("PRODUCT");
        when(product.getSearchTerm()).thenReturn("Java");
        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        List<SearchResult> results = new ArrayList<>(searchService.search("java"));
        assertEquals(1, results.size());
        assertEquals("Java для чайников", results.get(0).getName());
    }
}