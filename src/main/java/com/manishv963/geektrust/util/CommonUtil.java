package com.manishv963.geektrust.util;

import org.joda.time.LocalTime;

import com.manishv963.geektrust.enums.BookingTypeEnum;
import com.manishv963.geektrust.exceptions.InvalidInputException;

public class CommonUtil {

	public static String getBookingType(String bookingType) throws InvalidInputException {
		try {
			BookingTypeEnum userValueEnum = BookingTypeEnum.valueOf(bookingType);
			switch (userValueEnum) {

			case BOOK:
				return BookingTypeEnum.BOOK.toString();
			case VACANCY:
				return BookingTypeEnum.VACANCY.toString();
			default:
				throw new InvalidInputException("Invalid Booking Type");

			}
		} catch (Exception e) {
			throw new InvalidInputException("Invalid Booking Type");

		}
	}
	
	public static LocalTime getFormattedTime(String time) throws InvalidInputException {
		try {
			int hour = Integer.parseInt(time.split(":")[0]);
			int minute = Integer.parseInt(time.split(":")[1]);

			LocalTime formattedTime = new LocalTime(hour, minute);
			return formattedTime;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new InvalidInputException("Invalid Start/End Time");
		}

	}


}
