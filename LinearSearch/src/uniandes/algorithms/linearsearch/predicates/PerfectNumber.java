package uniandes.algorithms.linearsearch.predicates;

import java.util.ArrayList;

public class PerfectNumber implements uniandes.algorithms.linearsearch.Predicate{

	ArrayList<Integer> numbs = new ArrayList();
	ArrayList<Double> results = new ArrayList();
	
	public void setNumbs()
	{
		numbs.add(2);
		numbs.add(3);
		numbs.add(5);
		numbs.add(7);
		numbs.add(13);
		numbs.add(17);
		numbs.add(19);
		numbs.add(31);
		for (int i = 0; i < numbs.size(); i++) {
			results.add(Math.pow(2, numbs.get(i)-1) * (Math.pow(2, numbs.get(i)) - 1));
		}
	}
	
	@Override
	public boolean isTrue(Object o) {
		if (o == null) return false;
		double x = 0;
		double sumOfDivs = 0;
		if (o instanceof Integer)
			x = (Integer) o;
		if(o instanceof Double)
			x = (Double) o;
		if (x == 1 | x == 0) return false;
		for (int i = 1; i < x; i++)
			if (x%i == 0) sumOfDivs+=i;
		if (x == sumOfDivs) return true;
		return false;		
	}
	
	public boolean isPerfect(Object o) {
		if (o == null) return false;
		double x = 0;
		setNumbs();
		if (o instanceof Integer)
			x = (Integer) o;
		if(o instanceof Double)
			x = (Double) o;
		if (x == 1 | x == 0) return false;
		for (int i = 0; i < numbs.size(); i++)
		{
			double result = Math.pow(2, numbs.get(i)-1) * (Math.pow(2, numbs.get(i)) - 1);
			if (x == result) return true;
		}
		return false;
	}
	
}
