

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Graph exploration algorithm for the facebook exploration problem
 * @author Sergio Guzmán
 */
public class FacebookExplorationAlgorithm {

	//Input data
	private boolean[][] adj;
	
	//Bound for the decision problem
	private int boundNumberOfPeople;
	
	public boolean[] calculateOptimalSubset(boolean[][] adj) {
		//Input data is saved as class attributes to avoid passing the parameters through the different methods
		this.adj = adj;
		//To limit the number of solutions that will be compared in the cycle below
		//the suboptimal solution provided the greedy algorithm would be used as upper bound 
		this.boundNumberOfPeople = adj.length;
		//Call of the graph exploration algorithm to find feasible solutions
		List<FacebookState> solutions = findFeasibleSolutions();
		//Choose the best of the selected solutions
		FacebookState opt = null;
		int maxPeople = 0;
		for(FacebookState sol:solutions) {
			int people = sol.getTotalPeople();
			if(opt == null || maxPeople < people) {
				maxPeople = people;
				opt = sol;
			}
		}
		return opt.getPeople();
	}

	private List<FacebookState> findFeasibleSolutions() {
		List<FacebookState> answer = new ArrayList<>();
		//Initial state
		boolean [] people = new boolean [adj.length];
		Arrays.fill(people, false);
		FacebookState state = new FacebookState(people);
		//Agenda
		Queue<FacebookState> agenda = new LinkedList<>();
		agenda.add(state);
		while(agenda.size()>0) {
			//Choose next state from the agenda
			state = agenda.poll();
			if(isViable(state)) {
				if(isSolution(state)) answer.add(state);
				//Add successors to the agenda
				List<FacebookState> successors = getSuccessors (state);
				agenda.addAll(successors);
			}
		}
		return answer;
	}
	/**
	 * Calculates the successors of the given state. Successors are all states formed adding
	 * one person to the graph
	 * @param state source state to define successors
	 * @return List<FacebookState> successors of the given state
	 */
	private List<FacebookState> getSuccessors(FacebookState state) {
		boolean [] people = Arrays.copyOf(state.getPeople(), state.getPeople().length);
		List<FacebookState> successors = new ArrayList<>(people.length);
		for(int i=0;i<people.length;i++) {
			if(!people[i])
			{
				people[i]=true;
				FacebookState suc = new FacebookState(people);
				successors.add(suc);
				people[i]=false;
			}
		}
		return successors;
	}
	/**
	 * Determines if the given state could lead to a solution. This function implements the branch and
	 * bound strategy within the graph exploration algorithm
	 * @param state that will be checked for viability. 
	 * @return boolean true if the total value of the given state is less or equal than the value to be completed
	 */
	private boolean isViable(FacebookState state) {
		return state.getTotalPeople() < boundNumberOfPeople;
	}

	/**
	 * Determines if the given state is a solution. Implements the satisfiability predicate of the
	 * graph exploration algorithm
	 * @param state that will be checked
	 * @return boolean true if the total value of the given state is equal to the value to be completed
	 */
	private boolean isSolution(FacebookState state) {
		boolean[] people=state.people;
		for(int i=0;i<adj.length;i++)
		{
			if(people[i])
			{
				for(int j=i;j<adj[0].length;j++)
				{
					if(people[j])
					{
						if(!adj[i][j])
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
}
/**
 * A specific state for the graph exploration defined as an array of marked people with friendships in the subgraph
 * @author Sergio Guzmán
 */
class FacebookState {
	boolean[] people;

	/**
	 * Creates a new state with the given configuration of people
	 * @param people Number of people of each denomination.
	 * This array is copied internally to allow posterior modifications
	 */
	public FacebookState(boolean[] people) {
		this.people = Arrays.copyOf(people, people.length);
	}
	/**
	 * Calculates the total number of people in this state
	 * @return in Quantity of people in the state
	 */
	public int getTotalPeople () {
		int sum = 0;
		for(int i=0;i<people.length;i++) {
			if(people[i]) sum++;
		}
		return sum;
	}
	/**
	 * Returns the number of people 
	 * @return int [] number of people . The array is returned as is
	 */
	public boolean[] getPeople() {
		return people;
	}
}
