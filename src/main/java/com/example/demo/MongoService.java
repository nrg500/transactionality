package com.example.demo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MongoService
{
  public MongoService(MongoTemplate mongoTemplate){
    this.mongoTemplate = mongoTemplate;
  }
  private MongoTemplate mongoTemplate;

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void saveStuff() {
    mongoTemplate.find(Query.query(Criteria.where("id").is("henk")), Banana.class);
    System.out.println("found the banana from thread " + Thread.currentThread().getName());
    mongoTemplate.save(new Banana("henk", "blue", Thread.currentThread().getName()));
    System.out.println("saved the banana from thread " + Thread.currentThread().getName());
  }
}
