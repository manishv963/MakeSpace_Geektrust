package com.manishv963.geektrust.services;

import com.manishv963.geektrust.exceptions.InvalidInputException;

public interface RoomBookingServiceInterface {

	public String initiateRoomBooking(String input);

	public Object getInputObject(String input) throws InvalidInputException;


}
