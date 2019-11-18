package okh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Solver {
	public static int getRandomNumber(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	public static int[][] getRandomIndex(int size) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		int[][] randomIndex = new int[size][1];
		
		for(int i = 1; i <= size; i++) {
			index.add(i);
		}
		
		for(int i = 0;i < randomIndex.length; i++) {
			int randomNumber = getRandomNumber(0, index.size());
			randomIndex[i][0] = index.get(randomNumber);
			index.remove(randomNumber);
		}
		
		return randomIndex;
	}
	
	public static void main(String[] args) {
//		int[][] arr = getRandomIndex(10);
//		
//		for(int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i][0] + " ");
//		}
//		
//		System.out.println();
//		int[][] arr2 = getRandomIndex(10);
//		
//		for(int i = 0; i < arr2.length; i++) {
//			System.out.print(arr2[i][0] + " ");
//		}
		
		int[] a = {1,2,3};
		int[] b = Arrays.copyOf(a, a.length);
		b[0] = 10;
		
		for(int i = 0; i < 3; i++) {
			System.out.println(a[i]+" "+b[i]);
		}
	}
}
