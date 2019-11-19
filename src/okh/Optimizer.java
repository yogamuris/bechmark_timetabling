package okh;

import okh.Utils;

public class Optimizer {
	public static void hillclimbing(String dir_stu, String dir_crs, int timeslot) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());	
		int [][] copyGraph = Utils.copyArray(cm.getMatrix());
        
		
		int jumlah_timeslot = timeslot; 
        // Start
        long startTime = System.nanoTime();
		Scheduler scheduler = new Scheduler(cs.getSize());
//		scheduler.timesloting(graph, jumlah_timeslot);
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		// End
		
		scheduler.printSchedule(cm.getDegree());
		
		int jumlah = cm.getJumlahStudent();
		int[][] jadwal = scheduler.getSchedule();
		int[][] gr = cm.getLD(copyGraph);
		
		int[][] initialSolution = cm.getRandomMatrix();
		double penaltyAwal = Utils.getPenalty(initialSolution, jadwal, jumlah);
		scheduler.timesloting(initialSolution, jumlah_timeslot);
		
		scheduler.printSchedule(cm.getRandomIndex(initialSolution.length));
		
		for(int i = 0; i < 1000; i++) {
			int[][] randomMatrix = cm.getRandomMatrix();
			double penaltyIterasi = Utils.getPenalty(randomMatrix, jadwal, jumlah);
			if(penaltyAwal > penaltyIterasi)
				penaltyAwal = penaltyIterasi;
			
			System.out.println(penaltyAwal);
		}
		
		
		System.out.println("Penalty : "+ penaltyAwal);
		System.out.println("Total Eksekusi : " + (double)totalTime/1000000000 + " detik");
		
	}
}
