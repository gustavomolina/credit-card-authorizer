package com.example.authorizer.controller;

import com.example.authorizer.model.Transaction;
import com.example.authorizer.service.AuthorizationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private AuthorizationService authorizationService;

    public TransactionControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processTransaction_Approved() {
        Transaction transaction = new Transaction();
        when(authorizationService.authorizeTransaction(transaction)).thenReturn("00");

        ResponseEntity<String> response = transactionController.authorizeTransaction(transaction);

        assertEquals("00", response.getBody());
    }

    @Test
    void processTransaction_InsufficientFunds() {
        Transaction transaction = new Transaction();
        when(authorizationService.authorizeTransaction(transaction)).thenReturn("51");

        ResponseEntity<String> response = transactionController.authorizeTransaction(transaction);

        assertEquals("51", response.getBody());
    }
}
