package uniandes.algorithms.linearsearch.predicates;

import uniandes.algorithms.linearsearch.Predicate;
/**
 * Concrete implementation of a predicate that tests if a number is greater than 10000
 * @author Jorge Duitama
 *
 */
public class GreaterThan10000 implements Predicate {

	@Override
	public boolean isTrue(Object o) {
		if (o==null) return false;
		double x=0;
		if (o instanceof Integer) {
			x = (Integer)o;
		}
		if (o instanceof Double) {
			x = (Double)o;
		}
		return x>10000;
	}
}
