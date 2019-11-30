package SimulatedAnnealing;

import okh.Utils;

public class SimulatedAnnealing {
	public static int[][] run(int[][] matrix, int jumlahSiswa, int[][] solution, int maxTemperature) {
		int[][] s = solution;
		int[][] bestSolution = s;
		
		double t = maxTemperature;
		double minTemperature = 0;
		int maxIteration = 100;
		double reductionFactor = 0.01;
		
		while(t > minTemperature) {
			int i = 0;
			
			while(i < maxIteration) {
				int[][] s2 = Utils.swap(solution, 2);
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
			t = t * reductionFactor;
		}
		
		
		return bestSolution;
	}
}
