package uniandes.algorithms.linearsearch;

import java.util.Iterator;

/**
 * Main class for the linear search example. Implements a generic search 
 * with dynamic enumerations and predicates 
 * @author Jorge Duitama
 *
 */
public class LinearSearchExample {

	/**
	 * Maximum number of elements to be iterated. Prevents infinite cycles
	 */
	private int maxElements = 10000000; 
	/**
	 * Main method for the linear search example 
	 * It requires two parameters:
	 * args[0]: Fully qualified class name of the iterator to run
	 * args[1]: Fully qualified class name of the predicate to validate
	 * @param args Array with the arguments described above
	 * @throws Exception If either class is not found or not casted correctly
	 */
	public static void main(String[] args) throws Exception {
		LinearSearchExample instance = new LinearSearchExample();
		String iteratorClassName = args[0];
		String predicateClassName = args[1];
		Iterator it;
		try {
			it = (Iterator)Class.forName(iteratorClassName).newInstance();
		} catch (ClassNotFoundException e) {
			throw new Exception("The class with the name: "+iteratorClassName+" could not be found",e);
		} catch (ClassCastException e ) {
			throw new Exception("The class with the name: "+iteratorClassName+" is not an iterator",e);
		}
		Predicate p;
		try {
			p = (Predicate)Class.forName(predicateClassName).newInstance();
		} catch (ClassNotFoundException e) {
			throw new Exception("The class with the name: "+predicateClassName+" could not be found",e);
		} catch (ClassCastException e ) {
			throw new Exception("The class with the name: "+predicateClassName+" is not a predicate",e);
		}
		Object o = instance.search (it,p);
		if(o==null) System.err.println("Error: Object not found");
		System.out.println(o.toString());
	}
	/**
	 * Finds the first element in the enumeration described by the given Iterator 
	 * that makes true the given predicate
	 * @param it Iterator that defines an enumeration 
	 * @param p Search predicate
	 * @return Object First element in the enumeration that makes true the given predicate
	 */
	public Object search(Iterator it, Predicate p) {
		for (int i=0;i<maxElements && it.hasNext(); i++) {
			Object o = it.next();
			System.out.println("Next element: "+o);
			if(p.isTrue(o)) return o;
		}
		return null;
	}

}
