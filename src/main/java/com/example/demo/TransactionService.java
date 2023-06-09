package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
  private MongoService mongoService;
  public TransactionService(MongoService mongoService) {
    this.mongoService = mongoService;
  }

  @Transactional
  public void doStuffInTransaction() {
    mongoService.saveStuff();
  }
}
