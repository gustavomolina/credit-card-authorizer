package com.example.authorizer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void transactionSetterGetter() {
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setMcc("5811");

        assertEquals(100.0, transaction.getAmount());
        assertEquals("5811", transaction.getMcc());
    }
}
