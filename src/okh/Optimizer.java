package okh;

import java.util.Arrays;

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
		
		int[][] gr = cm.getLD(copyGraph);
		
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
			
			int[][] grIter = cm.getLD(copyGraphIter);
			
			double penalty2 = Utils.getPenalty(grIter, jadwalIter, jumlah);
			
			if(penalty > penalty2)
				penalty = penalty2;
			
			System.out.println("Iterasi "+(i+1)+" - Penalty : "+penalty);
		}
	}
	
	public static void hillClimbing(String dir_stu, String dir_crs, int timeslot) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());
		
		int [][] copyGraph = Utils.copyArray(cm.getMatrix());
		int [][] graph = cm.getLargestDegree();
		
		int jumlahTimeslot = timeslot;
		Scheduler scheduler = new Scheduler(cs.getSize());
		scheduler.timesloting(graph, jumlahTimeslot);
		
		scheduler.printSchedule(cm.getDegree());
		int jumlah = cm.getJumlahStudent();
		int[][] jadwal = scheduler.getSchedule();
		
		int[][] gr = cm.getLD(copyGraph);
		
		double penalty = Utils.getPenalty(gr, jadwal, jumlah);
		System.out.println(penalty);
		
		int[] courseSeq = new int[jadwal.length];
		int[] timeslotSeq = new int[jadwal.length];
		
		for(int i = 0; i<jadwal.length; i++) {
			courseSeq[i] = jadwal[i][0];
			timeslotSeq[i] = jadwal[i][1];
		}

		int[][] jadwalTerbaik = new int[jadwal.length][2];
		
		for(int i = 0; i < 1000; i++) {
			int randomCourseIndex = Utils.getRandomNumber(0, courseSeq.length);
			int randomTimeslot = Utils.getRandomNumber(0, Arrays.stream(timeslotSeq).max().getAsInt());
		
			
			
			CourseSet csIter = new CourseSet(dir_crs);
			ConflictMatrix cmIter = new ConflictMatrix(dir_stu, cs.getSize());
			
			int [][] copyGraphIter = Utils.copyArray(cmIter.getMatrix());
			int [][] graphIter = cm.getLargestDegree();
			
			Scheduler schedulerIter = new Scheduler(csIter.getSize());
			
			schedulerIter.timesloting(graphIter, jumlahTimeslot);
			schedulerIter.printSchedule(cm.getDegree());
			jadwal[randomCourseIndex][1] = randomTimeslot;
			
			
			int[][] grIter = cm.getLD(copyGraphIter);
			
			double penalty2 = Utils.getPenalty(grIter, jadwal, jumlah);
			
			if(penalty > penalty2) {
				jadwalTerbaik = jadwal;
				penalty = penalty2;
			}
			
			System.out.println("Iterasi "+(i+1)+" - Penalty : "+penalty2);
			
		}
		
		setJadwal(jadwalTerbaik);
		System.out.println(penalty);

	}
	
	public static void simulatedAnnealing() {
		
	}
	
}
