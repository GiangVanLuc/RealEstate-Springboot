package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionEntity> findByCodeAndCustomerId(String code, Long customerId);
    TransactionResponseDTO loadTransactionDetail(Long id);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
}
