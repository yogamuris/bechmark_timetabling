package SimulatedAnnealing;

import okh.Utils;

public class SimulatedAnnealing {
	public static int[][] run(int[][] matrix, int jumlahSiswa, int[][] solution, double temperature, int iterasi) {
		int[][] sCurrent = solution;
		int[][] sBest = Utils.copySolution(sCurrent);
		double reductionFactor = 0.001;
		double tempCurr = temperature;
		
		for(int i = 0; i < iterasi; i++) {
			int randomLLH = Utils.getRandomNumber(1, 5);
			int[][] sIterasi;
			
			switch(randomLLH) {
				case 1:
					sIterasi = Utils.move(sCurrent.clone(), 1);
					break;
				case 2:
					sIterasi = Utils.swap(sCurrent.clone(), 1);
					break;
				case 3:
					sIterasi = Utils.move(sCurrent.clone(), 2);
					break;
				case 4:
					sIterasi = Utils.swap(sCurrent.clone(), 3);
					break;
				case 5:
					sIterasi = Utils.move(sCurrent.clone(), 3);
					break;
				default:
					sIterasi = Utils.swap(sCurrent.clone(), 1);
					break;
			}
			
			tempCurr = tempCurr * (1 - reductionFactor);
			if(Utils.getPenalty(matrix, sIterasi, jumlahSiswa) <= Utils.getPenalty(matrix, sCurrent, jumlahSiswa)) {
				sCurrent = Utils.copySolution(sIterasi);
				if(Utils.getPenalty(matrix, sIterasi, jumlahSiswa) <= Utils.getPenalty(matrix, sBest, jumlahSiswa)) {
					sBest = Utils.copySolution(sIterasi);
				}
			} else if(Math.exp((Utils.getPenalty(matrix, sCurrent, jumlahSiswa) - Utils.getPenalty(matrix, sIterasi, jumlahSiswa))/tempCurr) > Math.random()) {
				sCurrent = Utils.copySolution(sIterasi);
			}
			
			System.out.println(i+" "+Utils.getPenalty(matrix, sCurrent, jumlahSiswa));
		}
			
		return sBest;
	}
}
