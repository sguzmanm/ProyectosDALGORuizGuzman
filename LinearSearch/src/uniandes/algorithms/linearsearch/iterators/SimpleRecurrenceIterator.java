package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;
/**
 * Iterator that represents the enumeration described by the recurrence equation x_n = 2*x_{n-1}
 * @author Jorge Duitama
 */
public class SimpleRecurrenceIterator implements Iterator {

	/**
	 * Next element of the enumeration
	 */
	private int x = 1;
	@Override
	public boolean hasNext() {
		return x>0;
	}

	@Override
	public Object next() {
		//Saves the number to return 
		Integer answer = x;
		//Updates x to the next element
		x = 2*x;
		//Returns number
		return answer;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
