package okh;

import MetaHeuristic.GeneticAlgorithm;

public class Mencoba {
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";
	public static void main(String[] args) {
		String dir_crs = DIREKTORI+"car-f-92.crs";
		String dir_stu = DIREKTORI+"car-f-92.stu";
		
		GeneticAlgorithm.run(dir_stu, dir_crs, 20, 1000);
		
		
//		int[][] a = new int[10][2];
//		a[0][0] = 0;
//		a[0][1] = 0;
//		a[1][0] = 1;
//		a[1][1] = 1;
//		a[2][0] = 2;
//		a[2][1] = 2;
//		a[3][0] = 3;
//		a[3][1] = 3;
//		a[4][0] = 4;
//		a[4][1] = 4;
//		a[5][0] = 5;
//		a[5][1] = 5;
//		a[6][0] = 6;
//		a[6][1] = 6;
//		a[7][0] = 7;
//		a[7][1] = 7;
//		a[8][0] = 8;
//		a[8][1] = 8;
//		a[9][0] = 9;
//		a[9][1] = 9;
////		a[10][0] = 10;
////		a[10][1] = 10;
//		
//		int n = a.length/2;
//		
//		int[][] b = Utils.copySolution(a);
//		for(int i = 0; i < b.length; i++) {
//			b[i][1] = b[i][1]+10;
//		}
//		int[][] tempa = Utils.copySolution(a);
//		int[][] tempb = Utils.copySolution(b);
//		
//		for(int i=0; i < n; i++) {
//			a[i][1] = tempb[tempb.length-n+i][1];
//			b[tempb.length-n+i][1] = tempa[i][1];
//		}
//		
//		for(int i = 0; i < a.length; i++) {
//			System.out.println(a[i][0] + " " + a[i][1] + " \t\t" + b[i][0] + " " + b[i][1]);
//		}
	}
}
