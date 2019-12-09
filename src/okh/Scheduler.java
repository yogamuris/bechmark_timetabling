package okh;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.ArrayList;

public class Scheduler {
	private int size; 
    
    private boolean adaSolusi;
    private int[][] schedule;
    private String solusi = "";
    
    private int jumlahTimeslot;
    private int[] timeslot;
//    private ArrayList<Integer> timeslot;
    private int[] timetable;
    
    
    public Scheduler() {
    	this.size = 0;
    }
    
    public Scheduler(int size) {
    	this.size = size;
    }
    
    public void setJumlahTimeslot(int jumlahTimeslot) {
    	this.jumlahTimeslot = jumlahTimeslot;
    }
    
    public int getJumlahTimeslot() {
    	return this.jumlahTimeslot;
    } 
    
    public int[][] getSchedule() {
    	return this.schedule;
    }
    
    public String getSolusi() {
    	return this.solusi;
    }
    
    public int[] getTimetable() {
    	return this.timetable;
    }
    
    public int getSize() {
    	return this.size;
    }
    
    public void setSize(int size) {
    	this.size = size;
    }
    
    public boolean getAdaSolusi() {
    	return this.adaSolusi;
    }
    
    private void setAdaSolusi(boolean status) {
    	this.adaSolusi = status;
    }
    
//    public void initTimeslot(int size) {
//    	timeslot = new int[size];
//    	
//    	int ts = 1;
//    	
//    	for(int i = 0; i < timeslot.length; i++) {
//    		timeslot[i] = ts;
//    		ts++;
//    	}
//    }
    
    private boolean checkTimeslot(int course, int[][] matrix, int[] timeslot, int t) { 
        for (int i = 0; i < size; i++) 
            if (matrix[course][i] != 0 && t == timeslot[i]) 
                return false; 
        return true; 
    } 
    
    public static boolean checkRandomTimeslot(int randomCourse, int randomTimeslot, int[][] matrix, int[][] jadwal){
        for(int i=0; i<matrix.length; i++)
            if(matrix[randomCourse][i]!=0 && jadwal[i][1]==randomTimeslot)
                return false;
        return true;              
    }
  
    private boolean isTersedia(int[][] matrix, int jumlah_timeslot, int[] timeslot, int course) { 
        if (course == size) 
            return true; 
  
        for (int i = 1; i <= jumlah_timeslot; i++) { 
            if (checkTimeslot(course, matrix, timeslot, i)) { 
                timeslot[course] = i; 
 
                if (isTersedia(matrix, jumlah_timeslot, timeslot, course + 1)) 
                    return true; 
  
                timeslot[course] = 0; 
            } 
        } 
  
        return false; 
    } 
  
    public void timesloting(int[][] matrix, int jumlah_timeslot) { 
        timetable = new int[size]; 
        for (int i = 0; i < size; i++) 
            timetable[i] = 0; 
  
        if (isTersedia(matrix, jumlah_timeslot, timetable, 0))
            setAdaSolusi(true); 
        else
        	setAdaSolusi(false); 
    }
    
    public void printSchedule() { 
    	if (!adaSolusi)
    		System.out.println("Tidak ada solusi");
    	else {
    		System.out.println("Jumlah Timeslot : " + Arrays.stream(timetable).max().getAsInt());
    		for (int i = 0; i < size; i++) {
                System.out.println(+ (i+1) +" " + timetable[i]); 
                
    		}
            System.out.println(); 
    	}
    }
    
    public void printSchedule(int[][] degree) {
    	if (!adaSolusi)
    		System.out.println("Tidak ada solusi");
    	else {
    		schedule = new int[size][2];
    		for (int i = 0; i < size; i++) {
                solusi += degree[i][0] + " " + timetable[i] +"\n";
                schedule[i][0] = degree[i][0];
                schedule[i][1] = timetable[i];
    		}
    	}
    }
  
    
    public void exportSchedule(String filename) {
    	try{    
    		FileWriter fw=new FileWriter("D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/ExamTimetableEvaluation/"+filename+".sol");    
            fw.write(this.getSolusi());    
            fw.close();    
        } catch(Exception e){
        	System.out.println(e);
        }     
    }
    
    
}
