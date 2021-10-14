package com.manishv963.geektrust.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalTime;

import com.manishv963.geektrust.entities.Room;

public final class Constants {
	

	private final static int cCaveCapacity =3;
	private  final static int dTowerCapacity =7;
	private final static int gMansionCapacity = 20;
	public final static  int minCapacity = 2;
	public final  static int maxCapacity = 20;
	public final  static  String incorrectInputResult = "INCORRECT_INPUT";
	public final  static  String noVacantRoomResult = "NO_VACANT_ROOM";
	public final  static  String validationSuccess = "SUCCESS";
	public final  static Set<String> roomNamesSet =  new HashSet<String>(Arrays.asList("C-Cave","D-Tower", "G-Mansion"));
	public  final static List<Room> roomsList = new ArrayList<Room>(Arrays.asList(new Room("C-Cave",cCaveCapacity),
			new Room("D-Tower",dTowerCapacity),
			new Room("G-Mansion",gMansionCapacity)));

	public final static Set<LocalTime> bufferStartTimeList  = new HashSet<LocalTime>(Arrays.asList(new LocalTime(9,15),new LocalTime(13,15),new LocalTime(18,45)));
	public final static Set<LocalTime> bufferEndTimeList  = new HashSet<LocalTime>(Arrays.asList(new LocalTime(9,30),new LocalTime(13,45),new LocalTime(19,0)));
	public final static Set<LocalTime> bufferMidTimeList  = new HashSet<LocalTime>(Arrays.asList(new LocalTime(13,30)));

}
