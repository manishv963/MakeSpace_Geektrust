package com.geektrust.makespace.test.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.manishv963.geektrust.Geektrust;
import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.exceptions.InvalidInputException;
import com.manishv963.geektrust.repositories.RoomBookingRepository;
import com.manishv963.geektrust.services.RoomBookingService;
import com.manishv963.geektrust.util.Constants;

public class RoomBookingServiceTest {

	private RoomBookingService roomBookingService =new RoomBookingService();

	private RoomBookingRepository roomBookingRepository;
	
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
	public void testBookRoomWithCorrectInput() {
		String inputString = "VACANCY 20:00 23:45";
		String actualResult = roomBookingService.initiateRoomBooking(inputString);
		Assertions.assertEquals("C-Cave D-Tower G-Mansion", actualResult);
		inputString = "BOOK 00:00 03:00 6";
		actualResult = roomBookingService.initiateRoomBooking(inputString);
		Assertions.assertEquals("D-Tower", actualResult);

		inputString = "BOOK 22:00 23:30 18";
		actualResult = roomBookingService.initiateRoomBooking(inputString);
		Assertions.assertEquals("G-Mansion", actualResult);

		inputString = "BOOK 23:00 23:45 4";
		actualResult = roomBookingService.initiateRoomBooking(inputString);
		Assertions.assertEquals("D-Tower", actualResult);

		inputString = "VACANCY 22:00 23:45";
		actualResult = roomBookingService.initiateRoomBooking(inputString);
		Assertions.assertEquals("C-Cave", actualResult);


	}

	@Test
	public void testInitiateRoomBookingWithInCorrectInput() {
		String inputString = "VACANCY 22:00 01:45";
		String actualResult = roomBookingService.initiateRoomBooking(inputString);
		Assertions.assertEquals(Constants.incorrectInputResult, actualResult);
	}


	@Test
	public void testValidInputDTO() throws InvalidInputException {
		String inputBooking = "BOOK 22:00 22:15 10";
		Object actualObjectBooking = roomBookingService.getInputObject(inputBooking);
		String inputVacancy = "VACANCY 22:00 22:15 10";
		Object actualVacancyBooking = roomBookingService.getInputObject(inputVacancy);

		Assertions.assertAll("checking for allpossible valid input format ",
				() -> Assertions.assertEquals(BookingDetails.class, actualObjectBooking.getClass()),
				() -> Assertions.assertEquals(BookingDetails.class, actualObjectBooking.getClass()));

	}

	@Test
	public void testInvalidInputDTO() throws InvalidInputException {

		String inputString = "BOOK 23-00 23:45 4";
		String inputString1 = "BOK 23-00 23:45 4";

		String inputString2 = "BOOK23-00 23:45 4";

		String inputString3 = "VACANCY 23-00 23:45 21";

		Assertions.assertAll("testing all invalid input",
				() -> Assertions.assertThrows(InvalidInputException.class,
						() -> roomBookingService.getInputObject(inputString3)),
				() -> Assertions.assertThrows(InvalidInputException.class,
						() -> roomBookingService.getInputObject(inputString2)),
				() -> Assertions.assertThrows(InvalidInputException.class,
						() -> roomBookingService.getInputObject(inputString1)),
				() -> Assertions.assertThrows(InvalidInputException.class,
						() -> roomBookingService.getInputObject(inputString)));

	}
}
