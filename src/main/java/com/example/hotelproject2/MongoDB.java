package com.example.hotelproject2;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.*;
import org.bson.Document;

import com.mongodb.client.MongoClients;

import java.io.*;
import java.util.Properties;

public class MongoDB {
    public static void main( String[] args ) {
           String uri = System.getenv("apikey");
           try (MongoClient mongoClient = MongoClients.create(uri)) {
               MongoDatabase database = mongoClient.getDatabase("sample_mflix");
               MongoCollection<Document> collection = database.getCollection("movies");

               Document doc = collection.find(eq("title", "Back to the Future")).first();
               if (doc != null) {
                   System.out.println(doc.toJson());
               } else {
                   System.out.println("No matching documents found.");
               }
           }
        // Replace the placeholder with your MongoDB deployment's connection string


    }
}