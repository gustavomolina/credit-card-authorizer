package com.example.authorizer.service;

import com.example.authorizer.model.Category;
import com.example.authorizer.model.Transaction;
import com.example.authorizer.repository.CategoryRepository;
import com.example.authorizer.utils.CategoryType;
import com.example.authorizer.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private MCCMappingService mccMappingService;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorizeTransaction_Approved() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setMcc("1234");
        transaction.setAmount(100.0);
        transaction.setMerchant("Test Merchant");

        Category category = new Category();
        category.setCategoryId(CategoryType.FOOD);
        category.setTotalAmount(200.0);

        when(mccMappingService.mapMCCToCategory("1234", "Test Merchant")).thenReturn(CategoryType.FOOD);
        when(categoryRepository.findByCategoryId(CategoryType.FOOD)).thenReturn(Optional.of(category));

        // When
        String result = authorizationService.authorizeTransaction(transaction);

        // Then
        assertEquals("00", result);
        verify(categoryRepository, times(1)).save(category);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testAuthorizeTransaction_InsufficientFunds() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setMcc("1234");
        transaction.setAmount(300.0);
        transaction.setMerchant("Test Merchant");

        Category category = new Category();
        category.setCategoryId(CategoryType.FOOD);
        category.setTotalAmount(200.0);

        Category categoryCash = new Category();
        categoryCash.setCategoryId(CategoryType.CASH);
        categoryCash.setTotalAmount(250.0);

        when(mccMappingService.mapMCCToCategory("1234", "Test Merchant")).thenReturn(CategoryType.FOOD);
        when(categoryRepository.findByCategoryId(CategoryType.FOOD)).thenReturn(Optional.of(category));
        when(categoryRepository.findByCategoryId(CategoryType.CASH)).thenReturn(Optional.of(categoryCash));

        // When
        String result = authorizationService.authorizeTransaction(transaction);

        // Then
        assertEquals("51", result);
        verify(categoryRepository, times(0)).save(categoryCash);
        verify(transactionRepository, times(0)).save(transaction);
    }

    @Test
    void testAuthorizeTransaction_InsufficientFundsNoCash() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setMcc("1234");
        transaction.setAmount(300.0);
        transaction.setMerchant("Test Merchant");

        Category category = new Category();
        category.setCategoryId(CategoryType.FOOD);
        category.setTotalAmount(200.0);

        when(mccMappingService.mapMCCToCategory("1234", "Test Merchant")).thenReturn(CategoryType.FOOD);
        when(categoryRepository.findByCategoryId(CategoryType.FOOD)).thenReturn(Optional.of(category));
        when(categoryRepository.findByCategoryId(CategoryType.CASH)).thenReturn(Optional.of(category));

        // When
        String result = authorizationService.authorizeTransaction(transaction);

        // Then
        assertEquals("51", result);
        verify(categoryRepository, times(0)).save(any(Category.class));
        verify(transactionRepository, times(0)).save(transaction);
    }

    @Test
    void testAuthorizeTransaction_CategoryNotFound() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setMcc("1234");
        transaction.setAmount(100.0);
        transaction.setMerchant("Test Merchant");

        when(mccMappingService.mapMCCToCategory("1234", "Test Merchant")).thenReturn(CategoryType.FOOD);
        when(categoryRepository.findByCategoryId(CategoryType.FOOD)).thenReturn(Optional.empty());

        // When
        String result = authorizationService.authorizeTransaction(transaction);

        // Then
        assertEquals("07", result);
        verify(categoryRepository, times(0)).save(any(Category.class));
        verify(transactionRepository, times(0)).save(transaction);
    }
}
