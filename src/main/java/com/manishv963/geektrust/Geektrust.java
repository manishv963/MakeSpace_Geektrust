package com.manishv963.geektrust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.manishv963.geektrust.services.RoomBookingService;

public class Geektrust {

	
	public static void main(String[] args) throws IOException{

		File f = new File(args[0]);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
	        String bookingDetails;
	        
	        RoomBookingService roomBookingService = new RoomBookingService();
	        while ((bookingDetails = br.readLine()) != null) {
	        	System.out.println(roomBookingService.initiateRoomBooking(bookingDetails.trim()));    
	        	
	        }
		
		
		
	}
}
