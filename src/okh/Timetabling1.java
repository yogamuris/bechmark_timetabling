package okh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import okh.Utils;
import okh.Optimizer;
/**
 * 
 * @author Muris
 * Simulasi Timetabling
 */

public class Timetabling1 {
	
	static final String DIREKTORI = "D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/";
	
	
	
	public static void execute(String dir_stu, String dir_crs, int timeslot) {
		
		
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());	
		int [][] copyGraph = Utils.copyArray(cm.getMatrix());
		
		int [][] graph = cm.getLargestDegree();
        
		
		int jumlah_timeslot = timeslot; 
        // Start
        long startTime = System.nanoTime();
		Scheduler scheduler = new Scheduler(cs.getSize());
		scheduler.timesloting(graph, jumlah_timeslot);
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		// End
		
		scheduler.printSchedule(cm.getDegree());
		int jumlah = cm.getJumlahStudent();
		int[][] jadwal = scheduler.getSchedule();
		
		int[][] gr = cm.getLD(copyGraph);
		
		scheduler.exportSchedule(dir_stu.substring(dir_stu.length()-12, dir_stu.length()-4));
		System.out.println(jumlah);
		System.out.println("Penalty : " + Utils.getPenalty(gr, jadwal, jumlah));
		System.out.println("Total Eksekusi : " + (double)totalTime/1000000000 + " detik");
	}
	
	
	
	public static double getPenalty2(String filename) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"D:/KULIAH/ITS/Semester 7/Optimasi Kombinatorik dan Heuristik [OKH]/Tugas/Heuristik/Toronto/ExamTimetableEvaluation\" && ExamEvaluate "+filename);
		builder.redirectErrorStream(true);
	    Process p = builder.start();
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String line;
	    String result = "";
	    while ((line = r.readLine()) != null) {
	    	result = line.substring(33, 40);
	    }
	    double res = Double.parseDouble(result);
	    return res;
	}
	
	public static void executeOptimizer(String dir_stu, String dir_crs, int timeslot, String filename) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());
		
		int [][] graph = cm.getRandomMatrix();
        int jumlah_timeslot = timeslot;
        
		Scheduler scheduler = new Scheduler(cs.getSize());
		scheduler.timesloting(graph, jumlah_timeslot);
		
		
		scheduler.printSchedule(cm.getRandomIndex(graph.length));
		scheduler.exportSchedule(filename);
		
	}
	
	public static void main(String[] args) {
		// Direktori file
		String dir_carf92_stu = DIREKTORI+"car-f-92.stu";
		String dir_carf92_crs = DIREKTORI+"car-f-92.crs";
		
		Optimizer.randomSearch(dir_carf92_stu, dir_carf92_crs, 100);
		
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
		
		String dir_purs93_stu = DIREKTORI+"pur-s-93.stu";
		String dir_purs93_crs = DIREKTORI+"pur-s-93.crs";
		
		String dir_ryes93_stu = DIREKTORI+"rye-s-93.stu";
		String dir_ryes93_crs = DIREKTORI+"rye-s-93.crs";
		
		String dir_staf83_stu = DIREKTORI+"sta-f-83.stu";
		String dir_staf83_crs = DIREKTORI+"sta-f-83.crs";
		
		String dir_tres92_stu = DIREKTORI+"tre-s-92.stu";
		String dir_tres92_crs = DIREKTORI+"tre-s-92.crs";
		
		String dir_utas92_stu = DIREKTORI+"uta-s-92.stu";
		String dir_utas92_crs = DIREKTORI+"uta-s-92.crs";
		
		String dir_utes92_stu = DIREKTORI+"ute-s-92.stu";
		String dir_utes92_crs = DIREKTORI+"ute-s-92.crs";
		
		String dir_yorf83_stu = DIREKTORI+"yor-f-83.stu";
		String dir_yorf83_crs = DIREKTORI+"yor-f-83.crs";
		
		String test_stu = DIREKTORI+"test.stu";
		String test_crs = DIREKTORI+"test.crs";
		
//		CourseSet cs = new CourseSet(dir_carf92_crs);
//		ConflictMatrix cm = new ConflictMatrix(dir_carf92_stu, cs.getSize());
//		
//		int [][] graph = cm.getRandomMatrix();
//        int jumlah_timeslot = 100; 
//        
//        for(int i = 0; i < graph.length; i++) {
//        	for(int j = 0; j < graph.length; j++) {
//        		System.out.print(graph[i][j]+" ");
//        	}
//        	System.out.println();
//        }
//      
//        System.out.println(getPenalty(graph));
//        try {
//			getPenalty2("Carleton92");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Scheduler scheduler = new Scheduler(cs.getSize());
//		scheduler.timesloting(graph, jumlah_timeslot);
//		scheduler.printSchedule(cm.getRandomIndex(graph.length));
		
//		executeOptimizer(dir_carf92_stu, dir_carf92_crs, 100, "Carleton92");
		
		
//		double pen1 = Integer.MAX_VALUE;
//		for(int i = 0; i < 1000; i++) {
//			try {
//				
//				executeOptimizer(dir_carf92_stu, dir_carf92_crs, 100, "Carleton92");
//				double pen2 = getPenalty("Carleton92");
//				
//				if(pen2 < pen1)
//					pen1 = pen2;
//				
//				System.out.println(pen1);
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println(pen1);
		
		
		/*
		Scanner in = new Scanner(System.in);
		
		int timeslot = 0;
		
		System.out.println("=== ASIS TIMESLOTING === \n");
		System.out.println("1. Car-f-92");
		System.out.println("2. Car-s-91");
		System.out.println("3. Ear-f-83");
		System.out.println("4. Hec-s-92");
		System.out.println("5. Kfu-s-93");
		System.out.println("6. Lse-f-91");
		System.out.println("7. Pur-s-93");
		System.out.println("8. Rye-s-93");
		System.out.println("9. Sta-f-83");
		System.out.println("10. Tre-s-92");
		System.out.println("11. Uta-s-92");
		System.out.println("12. Ute-s-92");
		System.out.println("13. yor-f-83");
		System.out.println("99. EXIT");
		
		System.out.print("\nPilih File : ");
		int input = in.nextInt();
		
		switch(input) {
		case 1 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_carf92_stu, dir_carf92_crs, timeslot);
			break;
		case 2 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_cars91_stu, dir_cars91_crs, timeslot);
			break;
		case 3 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_earf83_stu, dir_earf83_crs, timeslot);
			break;
		case 4 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_hecs92_stu, dir_hecs92_crs, timeslot);
			break;
		case 5 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_kfus93_stu, dir_kfus93_crs, timeslot);
			break;
		case 6 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_lsef91_stu, dir_lsef91_crs, timeslot);
			break;
		case 7 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_purs93_stu, dir_purs93_crs, timeslot);
			break;
		case 8 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_ryes93_stu, dir_ryes93_crs, timeslot);
			break;
		case 9 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_staf83_stu, dir_staf83_crs, timeslot);
			break;
		case 10 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_tres92_stu, dir_tres92_crs, timeslot);
			break;
		case 11 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_utas92_stu, dir_utas92_crs, timeslot);
			break;
		case 12 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_utes92_stu, dir_utes92_crs, timeslot);
			break;
		case 13 :
			System.out.print("Jumlah Timeslot : ");
			timeslot = in.nextInt();
			execute(dir_yorf83_stu, dir_yorf83_crs, timeslot);
			break;
		case 99 :
			System.out.println("Bye...");
			System.exit(0);
		default :
			System.exit(0);
		}
		
		in.close();
		*/
	}
}
