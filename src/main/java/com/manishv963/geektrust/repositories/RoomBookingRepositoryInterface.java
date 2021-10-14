package com.manishv963.geektrust.repositories;

import java.util.List;

import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.entities.InputDetails;
import com.manishv963.geektrust.entities.Room;

public interface RoomBookingRepositoryInterface {

	
	
	public String confirmRoomBooking(BookingDetails newBookingDetails);
	public List<Room> getAvailableRooms(InputDetails newBookingDetails);
	public Room getRoomObject(String roomName);
	public boolean isRoomAvailable(InputDetails newBookingDetails, String roomName);
}
