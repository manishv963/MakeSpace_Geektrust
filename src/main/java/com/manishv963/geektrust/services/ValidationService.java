package com.manishv963.geektrust.services;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.entities.VacancyDetails;
import com.manishv963.geektrust.exceptions.InvalidInputException;
import com.manishv963.geektrust.util.Constants;

public class ValidationService implements ValidationServiceInterface {

	public String validateBookingDetailsObject(BookingDetails bookingDetails) {

		try {

			if (validateStartAndEndTime(bookingDetails.getStartTime(), bookingDetails.getEndTime()) == false) {
				// System.out.println("Invalid start or end time");
				return Constants.incorrectInputResult;
			} else if (checkForBufferTime(bookingDetails.getStartTime(), bookingDetails.getEndTime()) == false) {
				// System.out.println("Booking in buffeer time");
				return Constants.noVacantRoomResult;
			} else if (validateCapacity(bookingDetails.getCapacity(), Constants.minCapacity,
					Constants.maxCapacity) == false) {
				// System.out.println("Booking in buffeer time");
				return Constants.noVacantRoomResult;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.incorrectInputResult;
		}
		return Constants.validationSuccess;

	}

	public String validateVacancyDetailsObject(VacancyDetails vacancyDetails) {

		try {
			if (validateStartAndEndTime(vacancyDetails.getStartTime(), vacancyDetails.getEndTime()) == false) {
				// System.out.println("Invalid start or end time");
				return Constants.incorrectInputResult;
			} else if (checkForBufferTime(vacancyDetails.getStartTime(), vacancyDetails.getEndTime()) == false) {
				// System.out.println("Booking in buffeer time");
				return Constants.noVacantRoomResult;
			}

		} catch (Exception e) {

		}
		return Constants.validationSuccess;
	}

	public Boolean validateStartAndEndTime(LocalTime startTime, LocalTime endTime) throws InvalidInputException {
		int timeInterval = 15;
		int minuteDifference = Minutes.minutesBetween(startTime, endTime).getMinutes();
		if (endTime.getHourOfDay() < startTime.getHourOfDay()) {

			// throw new InvalidInputException("Start Time should be less than End Time");
			return false;
		}
		/*
		 * else if (startTime.getMinuteOfHour() % timeInterval != 0) throw new
		 * InvalidInputException(" A booking can be started and ended only in 15 minute intervals"
		 * ); else if ((endTime.getMinuteOfHour() - startTime.getMinuteOfHour()) %
		 * timeInterval != 0) { throw new
		 * InvalidInputException(" A booking can be started and ended only in 15 minute intervals"
		 * ); }
		 */

		else if (minuteDifference < timeInterval) {
			// throw new InvalidInputException(" A booking can be started and ended only in
			// 15 minute intervals");
			return false;

		} else if (startTime.getMinuteOfHour() % 15 != 0 || endTime.getMinuteOfHour() % 15 != 0) {
			// throw new InvalidInputException(" A booking can be started and ended only in
			// 15 minute intervals");
			return false;

		}
		return true;

	}

	public boolean validateCapacity(int userInput, int minCapacity, int maxCapacity) {

		if (userInput > maxCapacity)
			return false;
		else if (userInput < minCapacity) {
			return false;

		}
		return true;
	}

	public Boolean checkForBufferTime(LocalTime startTime, LocalTime endTime) {
		if (Constants.bufferStartTimeList.contains(startTime) || Constants.bufferMidTimeList.contains(startTime))
			return false;

		else if (Constants.bufferEndTimeList.contains(endTime) || Constants.bufferMidTimeList.contains(endTime))
			return false;

		return true;
	}
}
