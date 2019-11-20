package okh;

import okh.Utils;

public class Optimizer {
	private static int[][] jadwalTheBest;
	
	private static void setJadwal(int[][] jadwal) {
		jadwalTheBest = jadwal;
	}
	
	public static int[][] getJadwal() {
		return jadwalTheBest;
	}
	
	public static void randomSearch(String dir_stu, String dir_crs, int timeslot) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());
		
		int [][] copyGraph = Utils.copyArray(cm.getMatrix());
		int [][] graph = cm.getRandomMatrix();
		
		int jumlahTimeslot = timeslot;
		Scheduler scheduler = new Scheduler(cs.getSize());
		scheduler.timesloting(graph, jumlahTimeslot);
		
		scheduler.printSchedule(cm.getRandomIndex(graph.length));
		int jumlah = cm.getJumlahStudent();
		int[][] jadwal = scheduler.getSchedule();
		
		int[][] gr = cm.getLargestDegree(copyGraph);
		
		double penalty = Utils.getPenalty(gr, jadwal, jumlah);
		System.out.println(penalty);
		for(int i = 0; i < 1000; i++) {
			CourseSet csIter = new CourseSet(dir_crs);
			ConflictMatrix cmIter = new ConflictMatrix(dir_stu, cs.getSize());
			
			int [][] copyGraphIter = Utils.copyArray(cmIter.getMatrix());
			int [][] graphIter = cm.getRandomMatrix();
			
			Scheduler schedulerIter = new Scheduler(csIter.getSize());
			
			schedulerIter.timesloting(graphIter, jumlahTimeslot);
			schedulerIter.printSchedule(cm.getRandomIndex(graphIter.length));
			int[][] jadwalIter = schedulerIter.getSchedule();
			
			int[][] grIter = cm.getLargestDegree(copyGraphIter);
			
			double penalty2 = Utils.getPenalty(grIter, jadwalIter, jumlah);
			
			if(penalty > penalty2)
				penalty = penalty2;
			
			System.out.println("Iterasi "+(i+1)+" - Penalty : "+penalty);
		}
	}
	
	public static void hillClimbing(String dir_stu, String dir_crs, int timeslot, int iterasi) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());
		
		int [][] conflict_matrix = cm.getLargestDegree();
		int jumlahTimeslot = timeslot;
		
		Scheduler scheduler = new Scheduler(cs.getSize());
		scheduler.timesloting(conflict_matrix, jumlahTimeslot);
		scheduler.printSchedule(cm.getDegree());
		
		int jumlahStudent = cm.getJumlahStudent();
		
		int[][] jadwal = scheduler.getSchedule();
		int[][] jadwalTemp = new int[jadwal.length][2];
		
		for(int i = 0; i < jadwalTemp.length; i++) {
			jadwalTemp[i][0] = jadwal[i][0];
			jadwalTemp[i][1] = jadwal[i][1];
		}
		
		double penalty = Utils.getPenalty(conflict_matrix, jadwal, jumlahStudent);
		System.out.println(penalty);
		
		int max_timeslot = 0;
		
		for(int i = 0; i<jadwal.length; i++) {
			if(jadwal[i][1] > max_timeslot)
				max_timeslot = jadwal[i][1];
		}
		
		//
//		System.out.println("Init");
//		for(int i = 0; i < jadwal.length; i++) {
//			System.out.println(jadwal[i][0]+ " " + jadwal[i][1]);
//		}
//		
//		System.out.println();
		
		for(int i = 0; i < iterasi; i++) {
//			System.out.println("iterasi "+(i+1));
//			for(int j = 0; j < jadwal.length; j++) {
//				System.out.println(jadwal[j][0]+ " " + jadwal[j][1]);
//			}
			
			int randomCourseIndex = Utils.getRandomNumber(0, conflict_matrix.length);
			int randomTimeslot = Utils.getRandomNumber(0, max_timeslot);
//			System.out.println("random " + randomCourseIndex + " " + randomTimeslot);
			jadwalTemp[randomCourseIndex][1] = randomTimeslot;
		
//			System.out.println();
			
//			System.out.println(Scheduler.checkRandomTimeslot(randomCourseIndex, randomTimeslot, conflict_matrix, jadwal));
			if (Scheduler.checkRandomTimeslot(randomCourseIndex, randomTimeslot, conflict_matrix, jadwal)) {	
				jadwalTemp[randomCourseIndex][1] = randomTimeslot;
				double penalty2 = Utils.getPenalty(conflict_matrix, jadwalTemp, jumlahStudent);
//				System.out.println("penalty = "+penalty+", penalty 2 = "+penalty2);
				if(penalty > penalty2) {
					penalty = Utils.getPenalty(conflict_matrix, jadwalTemp, jumlahStudent);
					jadwal[randomCourseIndex][1] = jadwalTemp[randomCourseIndex][1];
				} else {
					jadwalTemp[randomCourseIndex][1] = jadwal[randomCourseIndex][1];
				}
			}
//			System.out.println("\n###\n");
			
			System.out.println("Iterasi "+(i+1)+" - Penalty : "+penalty);
		}
		
		setJadwal(jadwal);
		System.out.println(penalty);
	}
	
	public static void simulatedAnnealing() {
		
	}
	
}
