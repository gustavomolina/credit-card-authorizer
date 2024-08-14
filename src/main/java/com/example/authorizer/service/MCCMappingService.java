package com.example.authorizer.service;

import com.example.authorizer.model.Category;
import com.example.authorizer.utils.CategoryType;
import org.springframework.stereotype.Service;

@Service
public class MCCMappingService {

    public CategoryType mapMCCToCategory(String mcc, String merchant) {
        if (merchant.contains("UBER TRIP") || merchant.contains("PICPAY") || merchant.contains("PAG*")) {
            return CategoryType.CASH;
        } else if (merchant.contains("EATS")) {
            return CategoryType.FOOD;
        } else if (mcc.equals("5411") || mcc.equals("5412")) {
            return CategoryType.FOOD;
        } else if (mcc.equals("5811") || mcc.equals("5812")) {
            return CategoryType.MEAL;
        } else {
            return CategoryType.CASH;
        }
    }
}
