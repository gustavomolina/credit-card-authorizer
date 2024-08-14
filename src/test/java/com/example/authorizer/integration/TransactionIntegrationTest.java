//package com.example.authorizer.integration;
//
//import com.example.authorizer.model.Account;
//import com.example.authorizer.model.Balance;
//import com.example.authorizer.model.TransactionRequest;
//import com.example.authorizer.repository.AccountRepository;
//import com.example.authorizer.repository.BalanceRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TransactionIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private BalanceRepository balanceRepository;
//
//    @BeforeEach
//    void setUp() {
//        accountRepository.deleteAll();
//        balanceRepository.deleteAll();
//
//        accountRepository.save(new Account(1L, "123"));
//        balanceRepository.save(new Balance(1L, "FOOD", 150.00, accountRepository.findById(1L).orElse(null)));
//    }
//
//    @Test
//    void testTransactionApproval() {
//        TransactionRequest request = new TransactionRequest("123", 100.00, "5411", "PADARIA DO ZE               SAO PAULO BR");
//        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/authorize"), HttpMethod.POST, new HttpEntity<>(request), String.class);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("{\"code\":\"00\"}", response.getBody());
//    }
//
//    @Test
//    void testTransactionRejectionInsufficientFunds() {
//        TransactionRequest request = new TransactionRequest("123", 200.00, "5411", "PADARIA DO ZE               SAO PAULO BR");
//        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/authorize"), HttpMethod.POST, new HttpEntity<>(request), String.class);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("{\"code\":\"51\"}", response.getBody());
//    }
//
//    @Test
//    void testTransactionFallbackToCash() {
//        balanceRepository.save(new Balance(2L, "CASH", 300.00, accountRepository.findById(1L).orElse(null)));
//
//        TransactionRequest request = new TransactionRequest("123", 200.00, "5811", "UNKNOWN");
//        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/authorize"), HttpMethod.POST, new HttpEntity<>(request), String.class);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("{\"code\":\"00\"}", response.getBody());
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
//}
