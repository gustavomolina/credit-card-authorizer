package com.example.authorizer.service;

import com.example.authorizer.model.Category;
import com.example.authorizer.model.Transaction;
import com.example.authorizer.repository.CategoryRepository;
import com.example.authorizer.repository.TransactionRepository;
import com.example.authorizer.utils.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    MCCMappingService mccMappingService;

    public String authorizeTransaction(Transaction transaction) {
        CategoryType transactionCategory = mccMappingService.mapMCCToCategory(transaction.getMcc(), transaction.getMerchant());
        Optional<Category> categoryResult = categoryRepository.findByCategoryId(transactionCategory);

        if(categoryResult.isPresent()) {
            Category category= categoryResult.get();
            if (category.getTotalAmount() != null && category.getTotalAmount() >= transaction.getAmount()) {
                category.setTotalAmount(category.getTotalAmount() - transaction.getAmount());
                categoryRepository.save(category);
                transactionRepository.save(transaction);
                return "00"; // Transaction approved
            } else {
                Optional<Category> categoryCashResult = categoryRepository.findByCategoryId(CategoryType.CASH);
                if(categoryCashResult.isPresent()){
                    Category categoryCash = categoryCashResult.get();
                    if(categoryCash.getTotalAmount() >= transaction.getAmount()) {
                        categoryCash.setTotalAmount(category.getTotalAmount() - transaction.getAmount());
                        categoryRepository.save(categoryCash);
                        return "00";
                    }
                    return "51";
                }
                return "07";
            }
        } else
            return "07";
    }
}
