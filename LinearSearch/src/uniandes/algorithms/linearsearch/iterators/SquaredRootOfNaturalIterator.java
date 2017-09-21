package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;
/**
 * Succession of squared root from 0.
 * @author jc161
 *
 */
public class SquaredRootOfNaturalIterator implements Iterator{
	/**
	 * Nth element.
	 */
	private double n=0;
	/**
	 * Has a next element.
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return Math.sqrt(n)>=0;
	}
	/**
	 * Get the next element.
	 */
	@Override
	public Object next() {
		Double root= Math.sqrt(n);
		n++;
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Iterator p = new SquaredRootOfNaturalIterator();
		int i=0;
		while(p.hasNext() && i<10)
		{
			System.out.println((Double)p.next());
			i++;
		}
	}

}
