package puntos;

import java.util.PriorityQueue;

/**
 * 
 * @author Sergio Guzmán- Juan Camilo Ruiz
 *
 */
public class ProblemaC {
	
	private PriorityQueue<CharacterChainState> queue= new PriorityQueue<>();
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
			return t.length()-o.t.length();
		}
	}
	
	public String toString()
	{
		return t.length()+";"+sb.length+";"+n;
	}
}



