package okh;

import java.util.Arrays;
import java.util.Random;

public class Utils {
	public static int[][] copyArray(int[][] arr) {
		int[][] arrcop = new int[arr.length][arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				arrcop[i][j] = arr[i][j];
			}
		}
		
		return arrcop;
		
	}
	
	public static double getPenalty(int[][] matrix, int[][] jadwal, int jumlah) {
		double penalty = 0;
		
		for(int i = 0; i < matrix.length - 1; i++) {
			for(int j = i+1; j < matrix.length; j++) {
				if(matrix[i][j] != 0) {
					if(Math.abs(jadwal[j][1] - jadwal[i][1]) >= 1 && Math.abs(jadwal[j][1] - jadwal[i][1]) <= 5) {
						penalty = penalty + (matrix[i][j] * (Math.pow(2, 5-(Math.abs(jadwal[j][1] - jadwal[i][1])))));
					}
				}
			}
		}
		
		return penalty/jumlah;
	}
	
	public static int getRandomNumber(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	public static int[][] move(int[][] solution, int step) {
		int[][] temp = solution;
		int[] timeslot = new int[temp.length];
		for(int i = 0; i < temp.length; i++) {
			timeslot[i] = temp[i][1];
		}
		for(int i = 0; i < step; i++) {
			int randomExam = getRandomNumber(0, solution.length);
			int randomTimeslot = getRandomNumber(0,  Arrays.stream(timeslot).max().getAsInt());
			
			temp[randomExam][1] = randomTimeslot;
		}
		
		return temp;
	}
	
	public static int[][] swap(int[][] solution, int numSwap) {
		int[][] temp = solution;
		
		for(int i=0; i < numSwap; i++) {
			int exam1 = getRandomNumber(0, solution.length);
			int exam2 = getRandomNumber(0, solution.length);
			
			int timeslot1 = solution[exam1][1];
			int timeslot2 = solution[exam2][1];
			
			temp[exam1][1] = timeslot2;
			temp[exam2][1] = timeslot1;
		}
		
		return temp;
	}
}
