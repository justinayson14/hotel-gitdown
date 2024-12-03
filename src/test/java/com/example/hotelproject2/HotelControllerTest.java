package com.example.hotelproject2;

import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.PresRoom;
import com.example.hotelproject2.models.StandardRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelControllerTest {
    private HotelController h;

    @BeforeEach
    void setUp() {
        h = new HotelController();
    }

    @Test
    void createRoomFoo() {
        assertNull(h.createRoom("FooBar", 14));
    }

    @Test
    void createRoom14() {
        assertEquals(14, h.createRoom("Standard", 14).getRoomNum());
    }

    @Test
    void createRoomStd() {
        assertEquals(StandardRoom.class, h.createRoom("Standard", 14).getClass());
    }

    @Test
    void createRoomDeluxe() {
        assertEquals(DeluxeRoom.class, h.createRoom("Deluxe", 14).getClass());
    }

    @Test
    void createRoomPres() {
        assertEquals(PresRoom.class, h.createRoom("Presidential", 14).getClass());
    }

    @Test
    void recRoomBySize0() {
        assertTrue(h.recRoomBySize(0).contains("separate"));
    }

    @Test
    void recRoomBySize2() {
        assertTrue(h.recRoomBySize(2).contains("Standard"));
    }

    @Test
    void recRoomBySize4() {
        assertTrue(h.recRoomBySize(4).contains("Deluxe"));
    }

    @Test
    void recRoomBySize6() {
        assertTrue(h.recRoomBySize(6).contains("Presidential"));
    }

    @Test
    void recRoomBySize10() {
        assertTrue(h.recRoomBySize(10).contains("separate"));
    }
}