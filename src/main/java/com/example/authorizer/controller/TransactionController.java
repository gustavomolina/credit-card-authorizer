package com.example.authorizer.controller;

import com.example.authorizer.model.Transaction;
import com.example.authorizer.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<String> authorizeTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(authorizationService.authorizeTransaction(transaction));
    }
}
