package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;

/**
 * Succession that represents the binary search succession:
 * xn = 1 + 2*xn/2 con X0 = 1
 * @author jc161
 *
 */
public class BinarySearchIterator implements Iterator {
	/**
	 * Attribute for x.
	 */
	private double x=0;
	/**
	 * Determines if it can continue.
	 */
	@Override
	public boolean hasNext() {
		
		return 1+2*x>0;
	}
	/**
	 * Gives next element.
	 */
	@Override
	public Object next() {
		Double rta=1+2*x;
		x=1+2*x;
		return rta;
	}
	
	@Override
	public void remove() {
		
	}
	

	

}
