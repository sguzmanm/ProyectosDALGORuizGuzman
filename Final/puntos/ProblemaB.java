package puntos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Sergio Guzmán- Juan Camilo Ruiz
 *
 */
public class ProblemaB {

	public static void main(String[] args) {
		Graph G1= new Graph("5 4 0 1 0 2 0 3 2 4");
		Graph G2= new Graph("4 3 0 1 0 2 0 3");
		System.out.println();
		System.out.println(Arrays.toString(G1.bipartition));
		System.out.println(Arrays.toString(G2.bipartition));
		boolean[][] pos=Graph.joinPartition(G1.bipartition, G2.bipartition);
		System.out.println(Arrays.toString(pos[0]));
		System.out.println(Arrays.toString(pos[1]));
	}
}

/**
 * Clase del grafo
 */
class Graph
{
	//Array for the boolean bipartition
	public boolean[] bipartition;
	/**
	 * Graph constructor that uses a given line with the structure (v e x1 y1 x1 y2... xk yk) to create a graph bipartition.<br>
	 * @param line Given line with the forma described above
	 */
	public Graph(String line)
	{
		//Data split O(n)
		String[] data=line.split(" ");
		//Number of vertexes
		int v=Integer.parseInt(data[0]);
		//Adjacency matrix
		boolean[][] adj= new boolean[v][v];
		//P:2<=i<data.length ^ for each pair until i there is an edge in adj O(v)
		for(int i=2;i<data.length;i+=2)
		{
			adj[Integer.parseInt(data[i])][Integer.parseInt(data[i+1])]=true;
			adj[Integer.parseInt(data[i+1])][Integer.parseInt(data[i])]=true;
		}
		//Marked array
		boolean[] marked=new boolean[v];
		bipartition= new boolean[v];
		//Regular queue with O(1) operations
		Queue<Integer> agenda=new LinkedList<>();
		int source=Integer.parseInt(data[2]);
		bipartition[source]=true;
		marked[source]=true;
		agenda.add(source);
		boolean color=false;
		//Cycle. Worst case O(v+e)
		while(!agenda.isEmpty())
		{
			source=agenda.poll();
			color=!bipartition[source];
			for(int i=0;i<v;i++)
			{
				if(adj[source][i] && !marked[i])
				{
					bipartition[i]=color;
					marked[i]=true;
					agenda.add(i);
				}
			}
		}
		
	}
	/**
	 * Method that joins two bipartite graphs into one showing the two possible alternatives.<br>
	 * @param part1 First bipartite graph.<br>
	 * @param part2 Second bipartite graph.<br>
	 * @return rta Boolean matrix that stores the tow possible scenarios of joining between classes.
	 */
	public static boolean[][] joinPartition(boolean[] part1, boolean[] part2)
	{
		//rta(a,b,c) function that returns the boolean subset in the array rta[a] from index b to index c
		//f(b,c) function that returns the boolean subset in the array b from until c
		//!f(b,c) complements of f(b,c)
		boolean[][] rta=new boolean[2][part1.length+part2.length];
		//P: 0<=i<part1.length ^ rta(0,0,i)=rta(1,0,i)=f(part1,i)
		//Regular cycle O(part1.v)
		for(int i=0;i<part1.length;i++)
		{
			rta[0][i]=part1[i];
			rta[1][i]=part1[i];
		}
		//P: part1.length<=i<part2.length+part1.length ^ rta(0,part1.length,i)=f(part2,i-part1.length) ^ rta(1,part1.length,i)=!f(part2,i-part1.length)
		//Regular cycle O(part2.v)
		for(int i=part1.length;i<part2.length+part1.length;i++)
		{
			rta[0][i]=part2[i-part1.length];
			rta[1][i]=!part2[i-part1.length];
		}
		return rta;
	}
}
