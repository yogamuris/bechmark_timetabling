package MetaHeuristic;

import okh.ConflictMatrix;
import okh.CourseSet;
import okh.Scheduler;
import okh.Utils;
import okh.Solution;
import java.util.ArrayList;

public class GeneticAlgorithm {
	public ArrayList<Solution> recombination(Solution s1, Solution s2) {
		int[][] sol1 = s1.getSolution();
		int[][] sol2 = s2.getSolution();
		
		int n = sol1.length/2;
		
		int[][] temp1 = Utils.copySolution(sol1);
		int[][] temp2 = Utils.copySolution(sol2);
				
		for(int i=0; i < n; i++) {
			sol1[i][1] = temp2[temp2.length-n+i][1];
			sol2[temp2.length-n+i][1] = temp1[i][1];
		}
		
		Solution solution1 = new Solution(sol1);
		Solution solution2 = new Solution(sol2);
		
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		solutions.add(solution1);
		solutions.add(solution2);
		
		return solutions;
	}
	
	int[][] mutation() {
		int[][] solution = null;
		
		return solution;
	}
	
	int[][] replication() {
		int[][] solution = null;
		
		return solution;
	}
	
	public static void run(String dir_stu, String dir_crs) {
		CourseSet cs = new CourseSet(dir_crs);
		ConflictMatrix cm = new ConflictMatrix(dir_stu, cs.getSize());
		
		ArrayList<Solution> initPopulation = new ArrayList<Solution>();
		ArrayList<Double> populationPenalty = new ArrayList<Double>();
		
		for(int i = 0; i < 10; i++) {
			int[][] index = cm.getRandomIndex(cs.getSize());
			int [][] matrix = cm.getRandomMatrix(index);
			
			int jumlahStudent = cm.getJumlahStudent();
			
			Scheduler scheduler = new Scheduler(cs.getSize());
			scheduler.timesloting(matrix, 100);
			scheduler.printSchedule(index);
			int[][] solution = scheduler.getSchedule();
			
			Solution s = new Solution(solution);
			initPopulation.add(s);
			
			double penalty = Utils.getPenalty(matrix, solution, jumlahStudent);
			s.setPenalty(penalty);
			populationPenalty.add(penalty);
		}
		
		initPopulation.sort((o1, o2) -> o1.getPenalty().compareTo(o2.getPenalty()));
		
		for(int i = 0; i < 10; i++) {
			System.out.println(initPopulation.get(i).getPenalty());
		}
		
		System.out.println();
		
		Solution bestSol = initPopulation.get(0);
		System.out.println(bestSol.getPenalty());
		
		
		
	}
	
}
