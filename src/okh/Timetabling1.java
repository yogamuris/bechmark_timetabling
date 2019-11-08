package okh;

/**
 * 
 * @author Muris
 * Simulasi Timetabling
 */

public class Timetabling1 {
	
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";
	
	public static void main(String[] args) {
		String dir_stu = DIREKTORI+"car-f-92.stu";
		String dir_crs = DIREKTORI+"car-f-92.crs";
		
		String test_stu = DIREKTORI+"test.stu";
		String test_crs = DIREKTORI+"test.crs";
	
		CourseSet carf92 = new CourseSet(dir_crs);
		CourseSet test = new CourseSet(test_crs);
		
		
		ConflictMatrix cm = new ConflictMatrix(dir_stu, carf92.getSize());
		
		ConflictMatrix cmTest = new ConflictMatrix(test_stu, test.getSize());
		
		int graph[][] = cm.getMatrixBiner();
		int [][] graph_test = cmTest.getMatrixBiner();
		
        int jumlah_timeslot = 6; 
		
        
		Scheduler scheduler = new Scheduler(test.getSize());
		scheduler.timesloting(graph_test, jumlah_timeslot);
		scheduler.printSchedule();
		
	}
}
