package com.epam.training.ticketservice.test.dto;

import com.epam.training.ticketservice.dto.RoomDto;
import com.epam.training.ticketservice.model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomDtoTest {

    @Test
    void testConstructorMapsRoomFieldsCorrectly() {
        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("Cinema Hall 1");
        when(mockRoom.getRows()).thenReturn(10);
        when(mockRoom.getColumns()).thenReturn(15);

        RoomDto roomDto = new RoomDto(mockRoom);

        assertEquals("Cinema Hall 1", roomDto.getName());
        assertEquals(10, roomDto.getRows());
        assertEquals(15, roomDto.getColumns());
    }

    @Test
    void testToStringFormatIsCorrect() {
        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("Main Hall");
        when(mockRoom.getRows()).thenReturn(8);
        when(mockRoom.getColumns()).thenReturn(12);

        RoomDto roomDto = new RoomDto(mockRoom);

        assertEquals("Room Main Hall with 96 seats, 8 rows and 12 columns", roomDto.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Room room1 = mock(Room.class);
        when(room1.getName()).thenReturn("Hall A");
        when(room1.getRows()).thenReturn(7);
        when(room1.getColumns()).thenReturn(10);

        Room room2 = mock(Room.class);
        when(room2.getName()).thenReturn("Hall A");
        when(room2.getRows()).thenReturn(7);
        when(room2.getColumns()).thenReturn(10);

        Room differentRoom = mock(Room.class);
        when(differentRoom.getName()).thenReturn("Hall B");
        when(differentRoom.getRows()).thenReturn(5);
        when(differentRoom.getColumns()).thenReturn(8);

        RoomDto dto1 = new RoomDto(room1);
        RoomDto dto2 = new RoomDto(room2);
        RoomDto differentDto = new RoomDto(differentRoom);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, differentDto);

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), differentDto.hashCode());
    }

    @Test
    void testGetters() {
        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("VIP Hall");
        when(mockRoom.getRows()).thenReturn(5);
        when(mockRoom.getColumns()).thenReturn(6);

        RoomDto roomDto = new RoomDto(mockRoom);

        assertEquals("VIP Hall", roomDto.getName());
        assertEquals(5, roomDto.getRows());
        assertEquals(6, roomDto.getColumns());
    }

    @Test
    void testToStringWithZeroSeats() {
        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("Empty Room");
        when(mockRoom.getRows()).thenReturn(0);
        when(mockRoom.getColumns()).thenReturn(0);

        RoomDto roomDto = new RoomDto(mockRoom);

        assertEquals("Room Empty Room with 0 seats, 0 rows and 0 columns", roomDto.toString());
    }
}
