package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;

/**
 * xn=(-1)n *⎣n/2⎦
 * @author jc161
 *
 */
public class SucessionIterator implements Iterator {
	/**
	 * Nth element.
	 */
	private double n=0;
	/**
	 * Has a next element.
	 */
	@Override
	public boolean hasNext() {
		return true;
	}
	/**
	 * Determines the next element.
	 */
	@Override
	public Object next() {
		
		Double rta= Math.pow(-1, n)*(n/2);
		n++;
		return rta;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Iterator p = new SucessionIterator();
		int i=0;
		while(p.hasNext() && i<10)
		{
			System.out.println((Double)p.next());
			i++;
		}
	}
	
	
}
