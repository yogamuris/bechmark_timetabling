package MetaHeuristic;

import okh.ConflictMatrix;
import okh.CourseSet;
import okh.Scheduler;
import okh.Utils;
import okh.Solution;

public class SimulatedAnnealing {
	
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
		int [][] confMat = cm.getConflictMatrix();
		int jumlahSiswa = cm.getJumlahStudent();
		
		int[][] solution = Scheduler.getSaturationSchedule(cs.getSize(), cm.getDegree(), confMat);
		
		Solution bestSolution = new Solution(solution);
		
		int[][] sCurrent = Utils.copySolution(solution);
		int[][] sBest = Utils.copySolution(sCurrent);
		double reductionFactor = 0.5;
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
			
		}
		
		System.out.println(bestSolution.getPenalty());

		setSolution(bestSolution);
	}
}
