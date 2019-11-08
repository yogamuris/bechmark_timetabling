package okh;



public class Scheduler {
	private int size; 
    private int[] timeslot; 
  
    public Scheduler(int size) {
    	this.size = size;
    }
    
    private boolean isSafe(int v, int[][] matrix, int[] timeslot, int c) { 
        for (int i = 0; i < size; i++) 
            if (matrix[v][i] == 1 && c == timeslot[i]) 
                return false; 
        return true; 
    } 
  
    private boolean matrixtimeslotingUtil(int[][] matrix, int m, int[] timeslot, int v) { 
        if (v == size) 
            return true; 
  
        for (int c = 1; c <= m; c++) { 
            if (isSafe(v, matrix, timeslot, c)) { 
                timeslot[v] = c; 
 
                if (matrixtimeslotingUtil(matrix, m, timeslot, v + 1)) 
                    return true; 
  
                timeslot[v] = 0; 
            } 
        } 
  
        return false; 
    } 
  
    public boolean matrixtimesloting(int[][] matrix, int m) { 
        timeslot = new int[size]; 
        for (int i = 0; i < size; i++) 
            timeslot[i] = 0; 
  
        if (!matrixtimeslotingUtil(matrix, m, timeslot, 0)) { 
            System.out.println("Tidak ada solusi"); 
            return false; 
        } 
  
        printSolution(timeslot); 
        return true; 
    } 
    
    private void printSolution(int[] timeslot) { 
        System.out.println("Solution Exists: Following" + 
                           " are the assigned timeslots"); 
        for (int i = 0; i < size; i++) 
            System.out.println("Course "+ (i+1) +" --- Timeslot " + timeslot[i] + " "); 
        System.out.println(); 
    }
}
