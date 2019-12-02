package TabuSearch;

public interface StopCondition {
	Boolean mustStop(Integer currentIteration, Solution bestSolutionFound);
}
