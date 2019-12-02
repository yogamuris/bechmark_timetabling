package TabuSearch;

import java.util.List;

public interface Solution {
	Double getValue();
	
	List<Solution> getNeighbors();
	
}
