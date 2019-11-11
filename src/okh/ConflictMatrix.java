package okh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class ConflictMatrix {
	int[][] conflict_matrix;
	
	public ConflictMatrix(String dir, int size) {
		conflict_matrix = new int[size][size];
		try {
			FileReader fr = new FileReader(dir);
			BufferedReader br = new BufferedReader(fr);
			createMatrix(br);
//			System.out.print(br);
		} catch(Exception e) {
			
		}	
	}
	
	public int[][] getMatrix() {
		return this.conflict_matrix;
	}
	
	public void createMatrix(BufferedReader br) {
		String courseLine = null;
		try {
			while((courseLine = br.readLine()) != null) {
				String[] arr = courseLine.split(" ");
				if(arr.length > 1) {
					for(int i = 0; i < arr.length-1; i++) {
						for(int j = i+1; j < arr.length; j++) {
							int index1 = Integer.parseInt(arr[i]);
							int index2 = Integer.parseInt(arr[j]);
							
							this.conflict_matrix[index1-1][index2-1]++;
							this.conflict_matrix[index2-1][index1-1]++;
						}
					}
				}
			}
			
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	
	public static void sortDegree(int arr[][], int col) { 
        Arrays.sort(arr, new Comparator<int[]>() { 
            
          @Override              
          public int compare(final int[] entry1, final int[] entry2) { 
            if (entry1[col] < entry2[col]) 
                return 1; 
            else
                return -1; 
          } 
        });
    } 
	
	public int[][] getLargestDegree() {
		int[][] temp = this.getMatrixBiner();
		int[][] courseDegree = new int[temp.length][2];
		
		for(int i = 0; i < temp.length; i++) {
			int count = 0;
			for(int j = 0; j < temp.length; j++) {
				if(temp[i][j] > 0)
					count++;
			}
			courseDegree[i][0] = i+1;
			courseDegree[i][1] = count;
		}
		sortDegree(courseDegree, 1);
		
		return courseDegree;
	}
	
	public int[][] getMatrixBiner() {
		int[][] temp = this.conflict_matrix;
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp.length; j++) {
				if(temp[i][j] > 0)
					temp[i][j] = 1;
			}
		}
		
		return temp;
	}
	
	public void printMatrix() {
		for(int i = 0; i < conflict_matrix.length; i++) {
			for(int j = 0; j < conflict_matrix.length; j++) {
				System.out.print(conflict_matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
