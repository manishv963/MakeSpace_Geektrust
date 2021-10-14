package com.manishv963.geektrust.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalTime;

public class Room implements Comparable<Room>{
	
	

	private String roomName;
	private Integer personCapacity;
	
	
	public Room(String roomName, int personCapacity) {
		this.roomName = roomName;
		this.personCapacity = personCapacity;
	}
	public String getRoomName() {
		return roomName;
	}
	public Integer getPersonCapacity() {
		return personCapacity;
	}
	@Override
	public String toString() {
		return "Room [roomName=" + roomName + ", personCapacity=" + personCapacity + "]";
	}
	@Override
	public int compareTo(Room o) {
		return getPersonCapacity().compareTo(o.getPersonCapacity());
	}  
}
