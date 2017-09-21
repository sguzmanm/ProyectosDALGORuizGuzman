package uniandes.algorithms.linearsearch.iterators;
import java.util.Iterator;

/**
 * Iterator that represents the enumeration of numbers that are squares of natural numbers
 * @author Jorge Duitama
 */
public class SquareNumbersIterator  implements Iterator {

	/**
	 * Index of the enumeration
	 */
	int n=1;
	@Override
	public boolean hasNext() {
		return n*n>0;
	}

	@Override
	public Object next() {
		//Calculates the number to return 
		Integer square = n*n;
		//Updates to next element
		n++;
		//Returns number
		return square;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
