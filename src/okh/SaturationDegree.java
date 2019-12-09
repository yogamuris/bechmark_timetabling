package okh;

import java.io.BufferedReader;
import java.io.FileReader;

public class SaturationDegree {
	private int size;
	private int jumlahTimeslot;
	private int[] timetable;
	private int[] timeslot;
	private int[][] schedule;
	private int[][] conflictMatrix;
	
	public SaturationDegree(String dir, int size) {
		conflictMatrix = new int[size][size];
		try {
			FileReader fr = new FileReader(dir);
			BufferedReader br = new BufferedReader(fr);
			
			createConflictMatrix(br);
			
		} catch(Exception e) {
			
		}
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setJumlahTimeslot(int jumlahTimeslot) {
		this.jumlahTimeslot = jumlahTimeslot;
	}
	
	public int getJumlahTimeslot() {
		return jumlahTimeslot;
	}
	
	public void createConflictMatrix(BufferedReader br) {
		
	}
	
	public void initTimeslot() {
		timeslot = new int[getJumlahTimeslot()];
	}
	
	public void initTimetable() {
		timetable = new int[getSize()];
	}
	
	
}
