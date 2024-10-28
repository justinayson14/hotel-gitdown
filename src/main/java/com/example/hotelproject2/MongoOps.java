package com.example.hotelproject2;

import com.example.hotelproject2.models.*;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
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

    public static Room queryAvailRoomByType(String roomType) {
        switch (roomType) {
            case "Deluxe":
                MongoCollection<DeluxeRoom> deluxe = db.getCollection("DeluxeRoom", DeluxeRoom.class);
                return deluxe.find(eq("occupied", false)).first();
            case "Presidential":
                MongoCollection<PresRoom> pres = db.getCollection("PresRoom", PresRoom.class);
                return pres.find(eq("occupied", false)).first();
            case "Standard":
                MongoCollection<StandardRoom> standard = db.getCollection("StandardRoom", StandardRoom.class);
                return standard.find(eq("occupied", false)).first();
            default:
                System.out.println("INVALID Room Type!!!");
                System.exit(1);
                return null;
        }
    }

    // create
    public static void checkInRoom (Class roomType, String roomId) {
        MongoCollection rooms = db.getCollection(roomType.getSimpleName(), roomType);
        rooms.updateOne(
                Filters.eq("_id", roomId),
                Updates.set("occupied", true)
        );
    }

    public static String queryCustomerIdByName (String value) {
        MongoCollection<Customers> collection = db.getCollection("Customers", Customers.class);
        return collection.find(eq("name", value)).first().getId();
    }

    public static void checkOutRoom (String customerId) {
        MongoCollection<Booking> bookings = db.getCollection("Booking", Booking.class);
        Booking selBooking = bookings.find(eq("customerId", customerId)).first();
        String roomId = selBooking.getRoomId();
        Class roomType = selBooking.getRoomClass();

        MongoCollection rooms = db.getCollection(roomType.getSimpleName());
        rooms.updateOne(
                Filters.eq("_id", roomId),
                Updates.set("occupied", false)
        );

    }

  /*  public static Room queryRoomsByType(String roomType) {
        MongoCollection<Room> rooms = db.getCollection("Rooms", Room.class);
        List<Room> availRooms =
    }*/

/*    public static void main (String[] args) {
        List<Customers> allCustomers = new ArrayList<>();
        MongoCollection<Customers>  customer = db.getCollection("Customers", Customers.class);
        for (Customers cust : customer.find()) {
            allCustomers.add(cust);
        }

        for (Customers cust : allCustomers) {
            System.out.println(cust.toString());
        }
    }*/
}