package okh;

public class Scheduler {
	private int size; 
    private int[] timeslot;
    private boolean adaSolusi;
  
    public Scheduler() {
    	this.size = 0;
    }
    
    public Scheduler(int size) {
    	this.size = size;
    }
    
    public int getSize() {
    	return this.size;
    }
    
    public void setSize(int size) {
    	this.size = size;
    }
    
    public boolean getAdaSolusi() {
    	return this.adaSolusi;
    }
    
    private void setAdaSolusi(boolean status) {
    	this.adaSolusi = status;
    }
    
    private boolean checkTimeslot(int vertex, int[][] matrix, int[] timeslot, int t) { 
        for (int i = 0; i < size; i++) 
            if (matrix[vertex][i] == 1 && t == timeslot[i]) 
                return false; 
        return true; 
    } 
  
    private boolean isTersedia(int[][] matrix, int jumlah_timeslot, int[] timeslot, int vertex) { 
        if (vertex == size) 
            return true; 
  
        for (int i = 1; i <= jumlah_timeslot; i++) { 
            if (checkTimeslot(vertex, matrix, timeslot, i)) { 
                timeslot[vertex] = i; 
 
                if (isTersedia(matrix, jumlah_timeslot, timeslot, vertex + 1)) 
                    return true; 
  
                timeslot[vertex] = 0; 
            } 
        } 
  
        return false; 
    } 
  
    public void timesloting(int[][] matrix, int jumlah_timeslot) { 
        timeslot = new int[size]; 
        for (int i = 0; i < size; i++) 
            timeslot[i] = 0; 
  
        if (!isTersedia(matrix, jumlah_timeslot, timeslot, 0))
            setAdaSolusi(false); 
        else
        	setAdaSolusi(true); 
    } 
    
    public void printSchedule() { 
    	if (!adaSolusi)
    		System.out.println("Tidak ada solusi");
    	else {
    		for (int i = 0; i < size; i++) {
                System.out.println("Course "+ (i+1) +" | Timeslot " + timeslot[i]); 
    		}
            System.out.println(); 
    	}
    
    }
}
