package com.example.demo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  protected String getDatabaseName() {
    return "gridCaseRegistry";
  }

  @Bean
  MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
    return new MongoTransactionManager(dbFactory);
  }

  @Override
  public MongoClient mongoClient() {
    MongoClientSettings.Builder builder = MongoClientSettings.builder();
    builder.uuidRepresentation(UuidRepresentation.JAVA_LEGACY);
    this.configureClientSettings(builder);
    builder.applyConnectionString(new ConnectionString("mongodb://localhost:27020/?tls=false&retryWrites=false"));
    return MongoClients.create(builder.build(), SpringDataMongoDB.driverInformation());
  }
}
