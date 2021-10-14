package com.manishv963.geektrust.services;

import org.joda.time.LocalTime;

import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.entities.VacancyDetails;
import com.manishv963.geektrust.enums.BookingTypeEnum;
import com.manishv963.geektrust.exceptions.InvalidInputException;
import com.manishv963.geektrust.repositories.RoomBookingRepository;
import com.manishv963.geektrust.util.CommonUtil;
import com.manishv963.geektrust.util.Constants;


public class RoomBookingService implements RoomBookingServiceInterface {

	private final RoomBookingRepository roomBookingRepository= new  RoomBookingRepository();

	private final ValidationService validationService = new ValidationService();



	public String initiateRoomBooking(String input) {
		String result = "";
		try {
			Object inputDetails = getInputObject(input);
		
			if (inputDetails.getClass().equals(BookingDetails.class)) {
				BookingDetails bookingDetails = (BookingDetails) inputDetails;
				result = validationService.validateBookingDetailsObject(bookingDetails);
				if (result.equals(Constants.validationSuccess))
					result = roomBookingRepository.confirmRoomBooking(bookingDetails);

			} else if (inputDetails.getClass().equals(VacancyDetails.class)) {
				VacancyDetails vacancyDetails = (VacancyDetails) inputDetails;
				result = validationService.validateVacancyDetailsObject(vacancyDetails);
				if (result.equals(Constants.validationSuccess))
					result = roomBookingRepository.getVacantRooms(vacancyDetails);
			}
			/*
			 * ValidationService validationService = new ValidationService(); if
			 * (bookingDetails.getBookingType().equals("BOOK")) { if
			 * (validationService.isValidCapacity(maximumPeopleCapacity,
			 * bookingDetails.getCapacity(), minimumPeopleCapacity) == false) { return
			 * "NO_VACANT_ROOM";
			 * 
			 * } } boolean isBufferTime =
			 * validationService.checkForBufferTime(bookingDetails.getStartTime(),
			 * bookingDetails.getEndTime()); if (isBufferTime == true) { result =
			 * "NO_VACANT_ROOM"; } else { result =
			 * roomBookingRepository.confirmRoomBooking(bookingDetails); }
			 */
		} catch (InvalidInputException e) {
			result = "INCORRECT_INPUT";
		}

		return result;
	}

	public Object getInputObject(String input) throws InvalidInputException {

		String[] inputArray = input.split(" ");
		try {
			String bookingType = CommonUtil.getBookingType(inputArray[0]);
			LocalTime startTime = CommonUtil.getFormattedTime(inputArray[1]);
			LocalTime endTime = CommonUtil.getFormattedTime(inputArray[2]);

			if (bookingType.equals(BookingTypeEnum.BOOK.toString())) {
				int requiredCapacity = Integer.parseInt(inputArray[3]);

				return new BookingDetails(bookingType, startTime, endTime, requiredCapacity);
			} else if (bookingType.equals(BookingTypeEnum.VACANCY.toString())) {
				return new VacancyDetails(bookingType, startTime, endTime);
			} else {
				throw new InvalidInputException("Invalid Booking Type");
			}

		}

		catch (InvalidInputException e) {
			// e.printStackTrace();
			throw new InvalidInputException("Error while getting BookingDetails");

		} catch (Exception e) {
			// e.printStackTrace();
			throw new InvalidInputException("Error while getting BookingDetails");

		}

	}

}
