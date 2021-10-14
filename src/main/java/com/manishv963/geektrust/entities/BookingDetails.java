package com.manishv963.geektrust.entities;

import org.joda.time.LocalTime;

import lombok.Data;

@Data
public class BookingDetails extends InputDetails {

	private int capacity;
	private String roomName;

	public BookingDetails(String bookingType, LocalTime startTime, LocalTime endTime, int requiredCapacity) {
		super(bookingType, startTime, endTime);
		this.capacity = requiredCapacity;
	}

	public BookingDetails(String bookingType, LocalTime startTime, LocalTime endTime, int requiredCapacity,
			String roomName) {
		super(bookingType, startTime, endTime);
		this.capacity = requiredCapacity;
		this.roomName = roomName;
	}
}
