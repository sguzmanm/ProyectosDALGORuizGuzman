package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;

/**
 * - Fibonnaci: xn = xn-1 + xn-2 con x0 = 0 y x1 = 1
 * @author jc161
 *
 */
public class FibonacciIterator implements Iterator {
	/**
	 * Nth element
	 */
	private int n=0;
	/**
	 * First element of Fibonnaci
	 */
	private double x0=0;
	/**
	 * Second element of Fibonnaci
	 */
	private double x1=1;
	/**
	 * Determines if it has a next element.
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return n>=0 && (x0+x1)>0;
	}
	/**
	 * Gives the next element.
	 */
	@Override
	public Object next() {
		if(n==0) 
		{
			n++;
			return x0;
		}
		else if (n==1) 
		{
			n++;
			return x1;
		}
		Double rta=x0+x1;
		x0=x1;
		x1=rta;
		return rta;
	}
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
