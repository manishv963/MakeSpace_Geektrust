package com.manishv963.geektrust.entities;

import org.joda.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDetails {

	
	
	private  String bookingType;
	private   LocalTime startTime;
	private   LocalTime endTime;
	

	

	
	
}
