package uniandes.algorithms.linearsearch.predicates;

import java.util.function.Predicate;

public class Pair implements uniandes.algorithms.linearsearch.Predicate{

	/**
	 * Predicado que prueba si el objeto recibido es un numero par
	 */
	@Override
	public boolean isTrue(Object o) {
		if (o == null) return false;
		double x = 0;
		if (o instanceof Integer)
			x = (Integer) o;
		if(o instanceof Double)
			x = (Double) o;
		if (x%2 == 0) return true;
		return false;
	}
	
	

}
