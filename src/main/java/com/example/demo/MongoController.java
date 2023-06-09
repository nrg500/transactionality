package com.example.demo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {

  private final MongoService mongoService;
  private MongoTemplate mongoTemplate;

  private TransactionService transactionService;

  public MongoController(MongoTemplate mongoTemplate, MongoService mongoService, TransactionService transactionService) {
    this.mongoTemplate = mongoTemplate;
    this.mongoService = mongoService;
    this.transactionService = transactionService;
  }

  @GetMapping("/")
  public void updateObject() {
    mongoTemplate.save(new Banana("henk", "yellow", "5"));

    Runnable r = new Runnable() {
      @Override
      public void run() {
        transactionService.doStuffInTransaction();
      }
    };
    for(int i = 0; i < 1000; i++){
      new Thread(r).start();
    }


  }
}
