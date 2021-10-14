package com.geektrust.makespace.test.validation;

import org.joda.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.manishv963.geektrust.Geektrust;
import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.entities.VacancyDetails;
import com.manishv963.geektrust.exceptions.InvalidInputException;
import com.manishv963.geektrust.services.ValidationService;
import com.manishv963.geektrust.util.Constants;
public class ValidationServiceTest {

	private ValidationService validationService = new ValidationService();
	@Test
	public void testValidateBookingDetailsObject() throws InvalidInputException {
		LocalTime startTime =new LocalTime(10,0);
		LocalTime inValidStartTime =new LocalTime(13,30);

		LocalTime endTime =new LocalTime(11,0);
		int validCapacity = 12;
		int inValidCapacity = 21;
		BookingDetails validBookingDetails = new BookingDetails("BOOK", startTime, endTime, validCapacity);
		String actualResult = validationService.validateBookingDetailsObject(validBookingDetails);
		String expectedResult = Constants.validationSuccess;
		Assertions.assertEquals(expectedResult, actualResult);
		
		BookingDetails invalidTimeBookingDetails = new BookingDetails("BOOK", inValidStartTime, endTime, validCapacity);
		String actualResult1 = validationService.validateBookingDetailsObject(invalidTimeBookingDetails);
		String expectedResult1 = Constants.incorrectInputResult;
		Assertions.assertEquals(expectedResult1, actualResult1);
		
		BookingDetails invalidTimeBookingDetails2 = new BookingDetails("BOOK", startTime, endTime, inValidCapacity);
		 actualResult1 = validationService.validateBookingDetailsObject(invalidTimeBookingDetails);
		Assertions.assertEquals(expectedResult1, actualResult1);
		
	
	}
	
	@Test
	public void testValidateVacancyObject() throws InvalidInputException {
		LocalTime startTime =new LocalTime(10,0);
		LocalTime inValidStartTime =new LocalTime(13,30);

		LocalTime endTime =new LocalTime(11,0);
		LocalTime inValidEndTime =new LocalTime(11,10);

		VacancyDetails validVacancyDetails = new VacancyDetails("VACANCY", startTime, endTime);
		String actualResult = validationService.validateVacancyDetailsObject(validVacancyDetails);
		String expectedResult = Constants.validationSuccess;
		Assertions.assertEquals(expectedResult, actualResult);
		
		VacancyDetails invalidStartTimeBookingDetails = new VacancyDetails("BOOK", inValidStartTime, endTime);
		String actualResult1 = validationService.validateVacancyDetailsObject(invalidStartTimeBookingDetails);
		String expectedResult1 = Constants.incorrectInputResult;
		Assertions.assertEquals(expectedResult1, actualResult1);
		
		VacancyDetails invalidEndTimeBookingDetails = new VacancyDetails("BOOK", startTime, inValidEndTime);
		 actualResult1 = validationService.validateVacancyDetailsObject(invalidEndTimeBookingDetails);
		Assertions.assertEquals(expectedResult1, actualResult1);
		
	}
	

}

