

import java.util.Arrays;

/**
 * Greedy algorithm.<br>
 * @author jc161
 *
 */
public class GreedyCoinChangeAlgorithm implements CoinChangeAlgorithm  {

	/**
	 * Calculates the number of coins of each denomination such as the total number of coins is minimal
	 * and the total value adds to the given number
	 * PRE: The list must be sorted by denomination and the first denomination must be 1 
	 * @param totalValue Total value to give back
	 * @param denominations Possible denominations of each coin.
	 * @return int number of coins to give back
	 */
	public int[]  calculateOptimalChange(int totalValue, int [] denominations)
	{
		//Last value
		int i=denominations.length-1;
		//Price
		int price=0;
		//Control over total value.
		int total=totalValue;
		int[] quantity=new int[denominations.length];
		int cant=0;
		while(price!=totalValue & i>=0)
		{
			cant=total/denominations[i];
			total-=denominations[i]*cant;
			price+=denominations[i]*cant;
			quantity[i]+=cant;
			i--;
		}
		return quantity;
	}
	
}
