package MetaHeuristic;

import okh.ConflictMatrix;
import okh.CourseSet;
import okh.Scheduler;
import okh.Utils;
import okh.Solution;

public class SimulatedAnnealing {
	/**
	 * Pseudocode dari http://www.cleveralgorithms.com/nature-inspired/physical/simulated_annealing.html
	 */
	
	private static Solution solusiTerbaik;
	
	private static void setSolution(Solution solusi) {
		solusiTerbaik = solusi;
	}
	
	public static Solution getSolusi() {
		return solusiTerbaik;
	}
	
	public static void run(String dir_stu, String dir_crs, double temperature, int iterasi) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());
		
//		int [][] matrix = cm.getLargestDegree();
		int [][] confMat = cm.getConflictMatrix();
		int jumlahSiswa = cm.getJumlahStudent();
//		Scheduler scheduler = new Scheduler(cs.getSize());
//		scheduler.timesloting(matrix, 100);
//		scheduler.printSchedule(cm.getDegree());
//		int[][] solution3 = scheduler.getSchedule();
		int[][] solution = Scheduler.getSaturationSchedule(cs.getSize(), cm.getDegree(), confMat);
		
		Solution bestSolution = new Solution(solution);
		
		int[][] sCurrent = Utils.copySolution(solution);
		int[][] sBest = Utils.copySolution(sCurrent);
		double reductionFactor = 0.001;
		double tempCurr = temperature;		
		
		for(int i = 0; i < iterasi; i++) {
			int randomLLH = Utils.getRandomNumber(1, 5);
			int[][] sIterasi;
			
			switch(randomLLH) {
				case 1:
					sIterasi = Utils.move(Utils.copySolution(sCurrent), 1);
					break;
				case 2:
					sIterasi = Utils.swap(Utils.copySolution(sCurrent), 1);
					break;
				case 3:
					sIterasi = Utils.move(Utils.copySolution(sCurrent), 2);
					break;
				case 4:
					sIterasi = Utils.swap(Utils.copySolution(sCurrent), 3);
					break;
				case 5:
					sIterasi = Utils.move(Utils.copySolution(sCurrent), 3);
					break;
				default:
					sIterasi = Utils.swap(Utils.copySolution(sCurrent), 1);
					break;
			}
			
			tempCurr = tempCurr * (1 - reductionFactor);
			if(Utils.isNotTabrakan(confMat, sIterasi)) {
				if(Utils.getPenalty(confMat, sIterasi, jumlahSiswa) <= Utils.getPenalty(confMat, sCurrent, jumlahSiswa)) {
					sCurrent = Utils.copySolution(sIterasi);
					if(Utils.getPenalty(confMat, sIterasi, jumlahSiswa) <= Utils.getPenalty(confMat, sBest, jumlahSiswa)) {
						sBest = Utils.copySolution(sIterasi);
						bestSolution.setSolution(sBest);
						bestSolution.setPenalty(Utils.getPenalty(confMat, sIterasi, jumlahSiswa));
					}
				} else if(Math.exp((Utils.getPenalty(confMat, sCurrent, jumlahSiswa) - Utils.getPenalty(confMat, sIterasi, jumlahSiswa))/tempCurr) > Math.random()) {
					sCurrent = Utils.copySolution(sIterasi);
				}
			}
			
			if((i+1) % 10 == 0)
				System.out.println("Iterasi ke-"+(i+1)+" "+Utils.getPenalty(confMat, sCurrent, jumlahSiswa));
			
		}
		System.out.println();
		System.out.println("Penalty initial solution : " + Utils.getPenalty(confMat, Utils.getSaturationSchedule(cs.getSize(), cm.getDegree(), confMat), jumlahSiswa));
		System.out.println("Penalty Terbaik : "+ bestSolution.getPenalty());
		System.out.println("Jumlah timeslot : " + bestSolution.getJumlahTimeslot());
//		int[][] bbest = bestSolution.getSolution();
//		
//		for(int i = 0; i < bbest.length; i++) {
//			System.out.println(bbest[i][0] + " " + bbest[i][1]);
//		}
		
		setSolution(bestSolution);
	}
}
