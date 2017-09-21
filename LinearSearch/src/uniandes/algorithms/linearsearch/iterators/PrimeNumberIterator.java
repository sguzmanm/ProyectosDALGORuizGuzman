package uniandes.algorithms.linearsearch.iterators;

import java.util.Iterator;

public class PrimeNumberIterator implements Iterator {
	/**
	 * Nth number.
	 */
	private int n=1;
	/**
	 * Has a next element.
	 */
	@Override
	public boolean hasNext() {		
		// TODO Auto-generated method stub
		return n>0;
	}
	/**
	 * Gives next element.
	 */
	@Override
	public Object next() {
		boolean next=false;
		int j=n;
		while(!next)
		{
			j++;
			next=true;
			for(int i=2;i<j;i++)
			{
				if(j%i==0)
				{
					next=false;
					break;
				}
			}
			if(next) n=j;
		}
		Integer rta=n;
		return rta;
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	

}
