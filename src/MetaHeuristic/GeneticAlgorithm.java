package MetaHeuristic;

import okh.ConflictMatrix;
import okh.CourseSet;
import okh.Scheduler;
import okh.Utils;
import okh.Solution;
import java.util.ArrayList;

public class GeneticAlgorithm {
	private static ArrayList<Solution> recombination(Solution s1, Solution s2) {
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
		
		int jumlahStudent = cm.getJumlahStudent();
		int[][] conflict_matrix = cm.getConflictMatrix();
		
		ArrayList<Solution> initPopulation = new ArrayList<Solution>();
		
		for(int i = 0; i < 10; i++) {
			int[][] index = cm.getRandomIndex(cs.getSize());
			int [][] matrix = cm.getRandomMatrix(index);			
			
			Scheduler scheduler = new Scheduler(cs.getSize());
			scheduler.timesloting(matrix, 100);
			scheduler.printSchedule(index);
			int[][] solution = scheduler.getSchedule();
			
			Solution s = new Solution(solution);
			initPopulation.add(s);
			
			double penalty = Utils.getPenalty(conflict_matrix, solution, jumlahStudent);
			s.setPenalty(penalty);
		}
		
		initPopulation.sort((o1, o2) -> o1.getPenalty().compareTo(o2.getPenalty()));
		
		System.out.println("Best fit solution : \n\t" + initPopulation.get(0).getPenalty() +"\n\t" + initPopulation.get(0).getJumlahTimeslot()); 
		System.out.println("2nd best fit solution : \n\t" + initPopulation.get(1).getPenalty() +"\n\t" + initPopulation.get(1).getJumlahTimeslot());
		
		// Recombination
		Solution solution1 = initPopulation.get(0);
		Solution solution2 = initPopulation.get(1);
		
		ArrayList<Solution> solRecombination = recombination(solution1, solution2);
		solution1 = solRecombination.get(0);
		solution2 = solRecombination.get(1);
		solution1.setPenalty(Utils.getPenalty(conflict_matrix, solution1.getSolution(), jumlahStudent));
		solution2.setPenalty(Utils.getPenalty(conflict_matrix, solution2.getSolution(), jumlahStudent));
		
		System.out.println("Solution 1 : \n\t" + solution1.getPenalty() +"\n\t" + solution1.getJumlahTimeslot()); 
		System.out.println("Solution 2 : \n\t" + solution2.getPenalty()+"\n\t"  + solution2.getJumlahTimeslot());
		
		
	}
	
}
