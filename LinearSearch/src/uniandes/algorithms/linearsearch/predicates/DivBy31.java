package uniandes.algorithms.linearsearch.predicates;

public class DivBy31 implements uniandes.algorithms.linearsearch.Predicate{

	/**
	 * Predicado que prueba si el objeto recibido es divisible por 31
	 */
	@Override
	public boolean isTrue(Object o) {
		if (o == null) return false;
		double x = 0;
		if (o instanceof Integer)
			x = (Integer) o;
		if(o instanceof Double)
			x = (Double) o;
		if (x%31 == 0) return true;
		return false;
	}

}
