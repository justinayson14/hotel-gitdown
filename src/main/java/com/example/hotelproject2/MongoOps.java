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
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Class that handles all CRUD operations on MongoDB database.
 * It imports all the necessary packages for handling Java POJOs and connecting to the MongoDB database.
 */
public class MongoOps {
    private static final ConnectionString connectionString = new ConnectionString(System.getenv("apiKey"));
    private static final CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    private static final CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    private static final MongoClientSettings clientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();
    private static final MongoClient mongoClient = MongoClients.create(clientSettings);
    private static final MongoDatabase db = mongoClient.getDatabase("hotel-gitdown");

    // make insertMultiple, insertSingle same name (insert)
    // inserts given data into database in their collection by their class

    /**
     * Takes in any type of Array List of objects and
     * inserts those objects into a collection named by its Class type in the MongoDB database
     * @param list List of items to insert to database
     */
    public static <T> void insertMultiple(ArrayList<T> list) {
        MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(list.get(0).getClass().getSimpleName(), list.get(0).getClass());
        collection.insertMany(list);
    }

    /**
     * Inserts an object of any type into a collection named by its Class type in the MongoDB database.
     * @param item Item to insert to database
     */
    public static <T> void insertSingle(T item) {
        MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(item.getClass().getSimpleName(), item.getClass());
        collection.insertOne(item);
    }

    /**
     * Gets the type of room and searches through the collection named by the room type and returns the first Room object
     * in that collection that is not occupied.
     * @param roomType Type of room to query
     * @return Room object that is not occupied
     */
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

    /**
     * Gets the room type and room ID and searches through the collection by its room type using the room id
     * and changes the value of occupied field to true
     * @param roomType For identifying the which room collection to search
     * @param roomId For identifying which room by id
     */
    public static void checkInRoom (String roomType, String roomId) {
        MongoCollection<Document> rooms = db.getCollection(roomType);
        rooms.updateOne(
                Filters.eq("_id", roomId),
                Updates.set("occupied", true)
        );
    }

    /**
     * Gets customer name and searches through Customers collection by the customer name and returns the customer id
     * @param name The name of the customer
     * @return String name of customer
     */
    public static String queryCustomerIdByName (String name) {
        MongoCollection<Customers> collection = db.getCollection("Customers", Customers.class);
        return Objects.requireNonNull(collection.find(eq("name", name)).first()).getId();
    }

    // finds booking by customerId, then finds room by roomId in booking to update room occupancy to false

    /**
     * Gets the customer id and searches for their booking in Bookings collection to get their reserved room id
     * and room type which is used to search through respective room collection to change occupied field of that room
     * from true to false.
     * @param customerId To find which booking is associated to customer ID
     */
    public static void checkOutRoom (String customerId) {
        MongoCollection<Booking> bookings = db.getCollection("Booking", Booking.class);
        Booking selBooking = bookings.find(eq("customerId", customerId)).first();
        assert selBooking != null;
        String roomId = selBooking.getRoomId();
        String roomType = selBooking.getRoomType();

        MongoCollection<Document> rooms = db.getCollection(roomType);
        rooms.updateOne(
                Filters.eq("_id", roomId),
                Updates.set("occupied", false)
        );

    }
}