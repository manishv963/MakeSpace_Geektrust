package com.geektrust.makespace.test.repository;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.manishv963.geektrust.Geektrust;
import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.entities.InputDetails;
import com.manishv963.geektrust.entities.Room;
import com.manishv963.geektrust.repositories.RoomBookingRepository;
import com.manishv963.geektrust.util.Constants;


public class RoomBookingRepositoryTest {

	private RoomBookingRepository roomBookingRepository = new RoomBookingRepository();
 	private List<BookingDetails> bookedRoomDetailsList =new ArrayList<BookingDetails>();
 	
	 
 	 void setupBookedRoomDetails() {
		BookingDetails bd1 = new BookingDetails("BOOK", new LocalTime(0,0), new LocalTime(3,0),6,"D-Tower");
		bookedRoomDetailsList.add(bd1);

		BookingDetails bd3=new BookingDetails("BOOK", new LocalTime(0,0), new LocalTime(3,0),2,"C-Cave");
		bookedRoomDetailsList.add(bd3);

		BookingDetails bd4 =new BookingDetails("BOOK", new LocalTime(0,0), new LocalTime(3,0),20,"G-Mansion");
		bookedRoomDetailsList.add(bd4);

		
		BookingDetails bd2 =new BookingDetails("BOOK", new LocalTime(22,0), new LocalTime(23,0),18,"G-Mansion");
		bookedRoomDetailsList.add(bd2);

		roomBookingRepository.bookedRoomDetailsList = bookedRoomDetailsList;

	
	}
	
	@Test
	public void testIsRoomAvailable(){
		setupBookedRoomDetails();
		InputDetails newConfirmedBookingDetails = new InputDetails("BOOK", new LocalTime(2,0), new LocalTime(11,0));
		String roomName="D-Tower";

		Assertions.assertEquals(false, roomBookingRepository.isRoomAvailable( newConfirmedBookingDetails, roomName));
	}

	
	@Test
	public void testIsRoomUnAvailable(){


		BookingDetails newConfirmedBookingDetails = new BookingDetails("BOOK", new LocalTime(22,0), new LocalTime(23,0),2);
		String roomName="D-Tower";
		Assertions.assertEquals(true, roomBookingRepository.isRoomAvailable(newConfirmedBookingDetails, roomName));
		
		 roomName="C-Cave";
		Assertions.assertEquals(true, roomBookingRepository.isRoomAvailable(newConfirmedBookingDetails, roomName));
	}
	
	@Test
	public void getListofAvailableRooms() {
		setupBookedRoomDetails();
		List<Room> expectedList =new ArrayList<Room>();
		List<Room> actualResult =new ArrayList<Room>();

		InputDetails newConfirmedBookingDetails = new InputDetails("BOOK", new LocalTime(22,0), new LocalTime(23,0));

		int cCaveCapacity = 3;
		expectedList.add(new Room("C-Cave",cCaveCapacity ));
		int dTowerCapacity = 7;
		expectedList.add(new Room("D-Tower",dTowerCapacity ));
		
		actualResult = roomBookingRepository.getAvailableRooms( newConfirmedBookingDetails);
		Assertions.assertEquals(expectedList.size(),actualResult.size());
		Assertions.assertEquals(expectedList.get(0).getRoomName(),actualResult.get(0).getRoomName());
	}
	
	@Test
	public void isValidRoomObject() {
		setupBookedRoomDetails();
		Room actualRoom = Constants.roomsList.get(0);
		Room expectedRoom = roomBookingRepository.getRoomObject("C-Cave");
		Assertions.assertEquals(expectedRoom.getRoomName(),actualRoom.getRoomName());
		
	}
	
	@Test
	public void checkVacantRoomConfirmation() {
		setupBookedRoomDetails();
		BookingDetails newConfirmedBookingDetails = new BookingDetails("BOOK", new LocalTime(2,0), new LocalTime(11,0),5);
		String actualResult=roomBookingRepository.confirmRoomBooking(newConfirmedBookingDetails);
		Assertions.assertEquals("NO_VACANT_ROOM",actualResult);
	}
	
	@Test
	public void checkValidRoomConfirmation() {
		String expectedResult="D-Tower";
		BookingDetails newConfirmedBookingDetails = new BookingDetails("BOOK", new LocalTime(23,0), new LocalTime(23,45),4);
		String actualResult = roomBookingRepository.confirmRoomBooking(newConfirmedBookingDetails);
		Assertions.assertEquals(expectedResult,actualResult);

	}
	
	
	
}
