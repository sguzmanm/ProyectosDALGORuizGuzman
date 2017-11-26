package puntos;

import java.util.PriorityQueue;

/**
 * 
 * @author Sergio Guzmán- Juan Camilo Ruiz
 *
 */
public class ProblemaC {
	//Java´s default PriorityQueue. It acts as a MinHeap with O(log n) operations for inserting and removing elements
	private PriorityQueue<CharacterChainState> agenda= new PriorityQueue<>();
}

class CharacterChainState implements Comparable<CharacterChainState>{
	//Text of the state
	private String t;
	//Substrings left to join
	private String[] sb;
	//Level of the state
	private int n;
	/**
	 * Constructor for the character chain state.<br>
	 * @param t Text of the state.<br>
	 * @param sb Substrings left to join<br>
	 * @param n Level of the state
	 */
	public CharacterChainState(String t, String[]sb, int n)
	{
		this.t=t;
		this.sb=sb;
		this.n=n;
	}
	/**
	 * Compares one state with another by checking their levels first and then the lengths of their characters.
	 */
	@Override
	public int compareTo(CharacterChainState o) {
		
		if(n<o.n) return-1;
		else if (n>o.n) return 1;
		else
		{
			//This is the worst case for the conditional because it involves to operations.
			//Nevertheless is assumed to be constant O(1)
			return t.length()-o.t.length();
		}
	}
	/**
	 * To string if it is required for further testing.<br>
	 * @return [Text length]:[Quantity of substrings];[Level]
	 */
	public String toString()
	{
		return t.length()+";"+sb.length+";"+n;
	}
}



