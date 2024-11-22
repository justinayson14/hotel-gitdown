package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Holds the personal details of a customer
 */
public class Customers {
    @BsonProperty(value = "_id")
    private String id = UUID.randomUUID().toString();
    private String name;
    private int partyCount;

    // public  empty constructor needed for retrieving POJO
    public Customers() {}
    public Customers(String name, int partyCount) {
        this.name = name;
        this.partyCount = partyCount;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPartyCount() {
        return partyCount;
    }
    public void setPartyCount(int partyCount) {
        this.partyCount = partyCount;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", partyCount=" + partyCount +
                '}';
    }
}