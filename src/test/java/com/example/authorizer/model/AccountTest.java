package com.example.authorizer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    @Test
    void accountIdSetterGetter() {
        Account account = new Account();
        account.setAccountName("123");

        assertEquals("123", account.getAccountName());
    }
}
