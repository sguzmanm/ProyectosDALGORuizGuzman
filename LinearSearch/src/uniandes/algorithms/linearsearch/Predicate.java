package uniandes.algorithms.linearsearch;

public interface Predicate {
	/**
	 * Tells if the predicate evaluated on the given object is true 
	 * @param o Object with values for the free variables of the predicate.
	 * Each concrete Predicate casts the object to an appropriate type.
	 * If the type is not appropriate, it should return false
	 * @return boolean true if the predicate is true for the given object, false otherwise
	 */
	public boolean isTrue(Object o);
}
