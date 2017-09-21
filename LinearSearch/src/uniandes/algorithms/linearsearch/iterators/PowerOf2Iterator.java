package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;
/**
 * Power of 2 succession.
 * @author jc161
 *
 */
public class PowerOf2Iterator implements Iterator {
	/**
	 * Nth element.
	 */
	private double n=0;
	/**
	 * Determines if there´s a next element.
	 */
	@Override
	public boolean hasNext() {
		return (Math.pow(2,n))>=1;
	}
	/**
	 * Determines the next element.
	 */
	@Override
	public Object next() {
		Double next=Math.pow(2, n);
		n++;
		return next;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
