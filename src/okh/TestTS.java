package okh;

import MetaHeuristic.*;

public class TestTS {
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";
	
	public static void main(String[] args) {
		String dir_carf92_stu = DIREKTORI+"car-f-92.stu";
		String dir_carf92_crs = DIREKTORI+"car-f-92.crs";
		
		String dir_cars91_stu = DIREKTORI+"car-s-91.stu";
		String dir_cars91_crs = DIREKTORI+"car-s-91.crs";
		
		String dir_earf83_stu = DIREKTORI+"ear-f-83.stu";
		String dir_earf83_crs = DIREKTORI+"ear-f-83.crs";
		
		String dir_hecs92_stu = DIREKTORI+"hec-s-92.stu";
		String dir_hecs92_crs = DIREKTORI+"hec-s-92.crs";
		
		String dir_kfus93_stu = DIREKTORI+"kfu-s-93.stu";
		String dir_kfus93_crs = DIREKTORI+"kfu-s-93.crs";
		
		String dir_lsef91_stu = DIREKTORI+"lse-f-91.stu";
		String dir_lsef91_crs = DIREKTORI+"lse-f-91.crs";
		
		long startTime2 = System.nanoTime();
		SimulatedAnnealing.run(dir_hecs92_stu, dir_hecs92_crs, 100, 3500);
		
		long endTime2   = System.nanoTime();
		long totalTime2 = endTime2 - startTime2;
		System.out.println("Total waktu : " + (double)totalTime2/1000000000 + " detik");
	
		
		
	}
}
