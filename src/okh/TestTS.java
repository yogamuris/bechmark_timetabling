package okh;

import SimulatedAnnealing.*;

public class TestTS {
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";
	
	public static void main(String[] args) {
		String dir_carf92_stu = DIREKTORI+"car-f-92.stu";
		String dir_carf92_crs = DIREKTORI+"car-f-92.crs";

		CourseSet cs = new CourseSet(dir_carf92_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_carf92_stu, cs.getSize());
		
		int [][] conflict_matrix = cm.getLargestDegree();
		int jumlahStudent = cm.getJumlahStudent();
		Scheduler scheduler = new Scheduler(cs.getSize());
		scheduler.timesloting(conflict_matrix, 100);
		scheduler.printSchedule(cm.getDegree());
		int[][] jadwal = scheduler.getSchedule();
		
		System.out.println(Utils.getPenalty(conflict_matrix, jadwal, jumlahStudent));
//		System.out.println(Utils.getPenalty(conflict_matrix, Utils.move(jadwal.clone(),2), jumlahStudent));
		
		int[][] bestSolution = SimulatedAnnealing.run(conflict_matrix, jumlahStudent, jadwal, 100, 10000);
		
		System.out.println(Utils.getPenalty(conflict_matrix, bestSolution, jumlahStudent));
	
		
	}
}
