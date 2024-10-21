package com.example.hotelproject2;

public class Customers {
    private String id;
    private String name;
    private int partyCount;

    // public  empty constructor needed for retrieving POJO
    public Customers() {}
    public Customers(String id, String name, int partyCount) {
        this.id = id;
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
}