package okh;

/**
 * 
 * @author Muris
 * Simulasi Timetabling
 */

public class Timetabling1 {
	
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";
	
	public static void main(String[] args) {
		String dir_carf92_stu = DIREKTORI+"car-f-92.stu";
		String dir_carf92_crs = DIREKTORI+"car-f-92.crs";
		
		String dir_cars91_stu = DIREKTORI+"car-s-91.stu";
		String dir_cars91_crs = DIREKTORI+"car-s-91.crs";
		
		String test_stu = DIREKTORI+"test.stu";
		String test_crs = DIREKTORI+"test.crs";
		
		CourseSet carf92 = new CourseSet(dir_carf92_crs);
		CourseSet test = new CourseSet(test_crs);
		CourseSet cars91 = new CourseSet(dir_cars91_crs);
		
		ConflictMatrix conflictMatrixCarf92 = new ConflictMatrix(dir_carf92_stu, carf92.getSize());
		ConflictMatrix conflictMatrixTest = new ConflictMatrix(test_stu, test.getSize());
		ConflictMatrix conflictMatrixCars91 = new ConflictMatrix(dir_cars91_stu, cars91.getSize());
		
//		conflictMatrixTest.getMatrixBiner();
//		conflictMatrixTest.printMatrix();
		
		int[][] degree = conflictMatrixCarf92.getLargestDegree();
//		for(int i = 0; i<degree.length; i++) {
//			for(int j = 0; j < degree.length; j++) {
//				System.out.print(degree[i][j]+ " ");
//			}
//			System.out.println();
//		}
//		
		
		int [][] graphCarf92 = conflictMatrixCarf92.getMatrixBiner();
		int [][] graphCars91 = conflictMatrixCars91.getMatrixBiner();
		int [][] graph_test = conflictMatrixTest.getMatrixBiner();
		
        int jumlah_timeslot = 50; 
		
		Scheduler scheduler = new Scheduler();
		scheduler.setSize(carf92.getSize());
		scheduler.timesloting(degree, jumlah_timeslot);
		scheduler.printSchedule();
//		int[] ts = scheduler.getTimeslot();
//		for(int i=0; i<ts.length; i++) {
//			System.out.println(ts[i]);
//		}
		
//		scheduler.setSize(carf92.getSize());
//		scheduler.timesloting(graphCarf92, 100);
//		scheduler.printSchedule();
	}
}
