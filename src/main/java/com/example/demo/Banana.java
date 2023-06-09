package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bananen")
public class Banana {

  @Id String id;

  String color;

  String size;

  public Banana(String id, String color, String size) {
    this.id = id;
    this.size = size;
    this.color = color;
  }
}
