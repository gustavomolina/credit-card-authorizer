package com.example.authorizer.service;

import com.example.authorizer.utils.CategoryType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MCCMappingServiceTest {

    private final MCCMappingService mccMappingService = new MCCMappingService();

    @Test
    void mapMCCToCategory_FOOD() {
        CategoryType category = mccMappingService.mapMCCToCategory("5411", "SUPERMARKET");
        assertEquals(CategoryType.FOOD, category);
    }

    @Test
    void mapMCCToCategory_MEAL() {
        CategoryType category = mccMappingService.mapMCCToCategory("5811", "RESTAURANT");
        assertEquals(CategoryType.MEAL, category);
    }

    @Test
    void mapMCCToCategory_CASH() {
        CategoryType category = mccMappingService.mapMCCToCategory("5999", "GENERIC STORE");
        assertEquals(CategoryType.CASH, category);
    }

    @Test
    void mapMCCToCategory_Default() {
        CategoryType category = mccMappingService.mapMCCToCategory("1234", "UNKNOWN");
        assertEquals(CategoryType.CASH, category);
    }
}
