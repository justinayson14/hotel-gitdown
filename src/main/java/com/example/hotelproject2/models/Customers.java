package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class Customers {
    @BsonProperty(value = "_id")
    private String id;
    private String name;
    private int partyCount;

    // public  empty constructor needed for retrieving POJO
    public Customers() {this.id = UUID.randomUUID().toString();}
    public Customers(String name, int partyCount) {
        this.id = UUID.randomUUID().toString();
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