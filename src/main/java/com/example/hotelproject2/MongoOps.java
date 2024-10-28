package com.example.hotelproject2;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoOps {
    private static final ConnectionString connectionString = new ConnectionString(System.getenv("apiKey"));
    private static final CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    private static final CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    private static final MongoClientSettings clientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();
    private static final MongoClient mongoClient = MongoClients.create(clientSettings);
    private static final MongoDatabase db = mongoClient.getDatabase("hotel-clients");

    public static <T> void insertMultiple(ArrayList<T> list) {
        MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(list.get(0).getClass().getSimpleName(), list.get(0).getClass());
        collection.insertMany(list);
    }

    public static <T> void insertSingle(T item) {
        MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(item.getClass().getSimpleName(), item.getClass());
        collection.insertOne(item);
    }
}