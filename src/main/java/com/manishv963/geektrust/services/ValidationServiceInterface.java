package com.manishv963.geektrust.services;

import org.joda.time.LocalTime;

import com.manishv963.geektrust.exceptions.InvalidInputException;

public interface ValidationServiceInterface {
	
	public Boolean validateStartAndEndTime(LocalTime startTime, LocalTime endTime) throws InvalidInputException;
	public boolean validateCapacity(int userInput, int minCapacity,int maxCapacity) ;
	public Boolean checkForBufferTime(LocalTime startTime, LocalTime endTime);
}
