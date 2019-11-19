package okh;

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
}
