package uniandes.algorithms.linearsearch.predicates;

public class PrimeNumber implements uniandes.algorithms.linearsearch.Predicate{

	/**
	 * Predicado que prueba si el objeto recibido es un numero primo
	 */
	@Override
	public boolean isTrue(Object o) {
		if (o == null) return false;
		double x = 0;
		if (o instanceof Integer)
			x = (Integer) o;
		if(o instanceof Double)
			x = (Double) o;
		if (x < 2) return false;
		for (int i = 2; i < x; i++)
			if (x%i == 0) return false;
		return true;
	}
}