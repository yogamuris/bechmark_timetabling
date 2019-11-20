package okh;

import okh.Optimizer;
/**
 * 
 * @author Muris
 * Simulasi Timetabling
 */

public class Timetabling1 {
	
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";	
	
	public static void main(String[] args) {
		// Direktori file
		String dir_carf92_stu = DIREKTORI+"car-f-92.stu";
		String dir_carf92_crs = DIREKTORI+"car-f-92.crs";
		
		long startTime = System.nanoTime();
		Optimizer.hillClimbing(dir_carf92_stu, dir_carf92_crs, 100, 100000);
		long endTime = System.nanoTime();
		System.out.println((double)(endTime-startTime)/1000000000);
		int[][] jadwal = Optimizer.getJadwal();
		for(int i = 0; i < jadwal.length; i++) {
			System.out.println(jadwal[i][0]+" "+jadwal[i][1]);
		}
	}
}
