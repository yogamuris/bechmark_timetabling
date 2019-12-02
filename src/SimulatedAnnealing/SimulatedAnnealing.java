package SimulatedAnnealing;

import okh.Utils;

public class SimulatedAnnealing {
	public static int[][] run(int[][] matrix, int jumlahSiswa, int[][] solution, int maxTemperature, int iterasi) {
		int[][] s = solution;
		int[][] bestSolution = s;
		
		double t = maxTemperature;
		double minTemperature = 1;
		int maxIteration = 100;
		double reductionFactor = 0.05;
		
		int randomLLH = Utils.getRandomNumber(1, 6);
		
		for(int it = 0; it < iterasi; it++) {
			while(t > minTemperature) {
				int i = 0;
				
				while(i < maxIteration) {
//					int[][] s2 = Utils.move(solution, 1);
					int[][] s2;
					switch(randomLLH) {
						case 1: 
							s2 = Utils.move(solution, 1);
						case 2: 
							s2 = Utils.swap(solution, 1);
						case 3: 
							s2 = Utils.move(solution, 2);
						case 4: 
							s2 = Utils.swap(solution, 3);
						case 5: 
							s2 = Utils.move(solution, 3);
						default: 
							s2 = Utils.swap(solution, 1);
					}
					
					double delta = Utils.getPenalty(matrix, s2, jumlahSiswa) - Utils.getPenalty(matrix, s, jumlahSiswa);
					if(delta < 0) {
						s = s2;
						if(Utils.getPenalty(matrix, s2, jumlahSiswa) < Utils.getPenalty(matrix, bestSolution, jumlahSiswa))
							bestSolution = s2;
					} else if(Math.random() < Math.exp(-delta/t)) {
						s = s2;
					}
					i++;
				}
				t = t * (1-reductionFactor);
			}
		}
			
		return bestSolution;
	}
}
