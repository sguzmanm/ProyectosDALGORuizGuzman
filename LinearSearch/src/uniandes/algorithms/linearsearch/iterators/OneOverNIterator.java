package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;
/**
 * Iterator for succession 1/n
 * @author jc161
 *
 */
public class OneOverNIterator implements Iterator{
	/**
	 *Nth element.
	 */
	private double n=1;
	/**
	 * Determines if there´s a next element.
	 */
	@Override
	public boolean hasNext() {
		return n>0;
	}
	/**
	 * Determines the next element.
	 */
	@Override
	public Object next() {
		Double next =1/(n);
		n++;
		return next;
	}
	
	@Override
	public void remove() {
		
	}
	
	

}
