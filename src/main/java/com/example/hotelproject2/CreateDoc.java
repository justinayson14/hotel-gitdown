package com.example.hotelproject2;

import com.example.hotelproject2.models.Customers;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class CreateDoc {
    private static ConnectionString connectionString = new ConnectionString(System.getenv("apiKey"));
    private static CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    private static CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    private static MongoClientSettings clientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();

    public static void main(String args[]) {
        Customers person1 = new Customers("123123", "Joe", 10);
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase("hotel-clients");
            MongoCollection<Customers> customers = db.getCollection("clients", Customers.class);
            customers.insertOne(person1);
        }

    }


}