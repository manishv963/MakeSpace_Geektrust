package com.manishv963.geektrust.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.manishv963.geektrust.entities.BookingDetails;
import com.manishv963.geektrust.entities.InputDetails;
import com.manishv963.geektrust.entities.Room;
import com.manishv963.geektrust.entities.VacancyDetails;
import com.manishv963.geektrust.util.Constants;





public class RoomBookingRepository implements RoomBookingRepositoryInterface {

	
	
	public List<BookingDetails> bookedRoomDetailsList =new ArrayList<BookingDetails>();

	
	
	public String confirmRoomBooking(BookingDetails newBookingDetails){
		
		String result ="NO_VACANT_ROOM";
		
		List<Room> availableRooms = getAvailableRooms(newBookingDetails);

			for (Room room : availableRooms) {
				if (room.getPersonCapacity() >= newBookingDetails.getCapacity()) {
					newBookingDetails.setRoomName(room.getRoomName());
					result = room.getRoomName();
					bookedRoomDetailsList.add(newBookingDetails);
					break;
				}

			}

			//System.out.println(availableRooms);
		
		return result;

	}
	
	public String getVacantRooms(VacancyDetails vacancyDetails){
		
		String result ="";
		
		List<Room> availableRooms = getAvailableRooms(vacancyDetails);
		if(availableRooms.size() < 1)
			return Constants.noVacantRoomResult;
		
		else {
			for (Room room : availableRooms) {
				result=result+room.getRoomName()+" ";
					
			}
		}
			//System.out.println(availableRooms);
		
		return result.trim();

	}
	
	
	public List<Room> getAvailableRooms(InputDetails newBookingDetails) {
		
		
		List<Room> availabeRooms =new ArrayList<Room>();
		for(String roomName : Constants.roomNamesSet ) {
			if(isRoomAvailable(newBookingDetails, roomName)) {
				availabeRooms.add(getRoomObject(roomName));
			}
			
			
		}
		Collections.sort(availabeRooms);
		return availabeRooms;
	}
	
	
	public Room getRoomObject(String roomName) {
		
		Room r = null;
		
		for(Room room:Constants.roomsList) {
			if(room.getRoomName().equals(roomName)) {
				r=room;
				break;
			}
		}
		return r;
	}
	
	public boolean isRoomAvailable(InputDetails newBookingDetails, String roomName) {
		
		
		for(BookingDetails bookedDetails:bookedRoomDetailsList) {
			if(bookedDetails.getRoomName().equals(roomName)) {
				if(newBookingDetails.getStartTime().isAfter(bookedDetails.getStartTime()) && 
						newBookingDetails.getStartTime().isBefore(bookedDetails.getEndTime())) {
					return false;
				}
				else if(newBookingDetails.getEndTime().isAfter(bookedDetails.getStartTime()) && 
						newBookingDetails.getEndTime().isBefore(bookedDetails.getEndTime())) {
					return false;
				}
				else if(newBookingDetails.getStartTime().equals(bookedDetails.getStartTime())){
					return false;
				}
				else if(newBookingDetails.getEndTime().equals(bookedDetails.getEndTime())){
					return false;
				}
			}
		}
		
		
		return true;
	}

	
}
