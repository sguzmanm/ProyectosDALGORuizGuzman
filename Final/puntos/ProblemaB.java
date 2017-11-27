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
		int[] pos=Graph.joinPartition1(G1.bipartition, G2.bipartition);
		int[] pos1=Graph.joinPartition2(G1.bipartition, G2.bipartition);
		System.out.println(pos[0]+" "+pos[1]);
		System.out.println(pos1[0]+" "+pos1[1]);
	}
}

/**
 * Clase del grafo
 */
class Graph
{
	//Array for the boolean bipartition
	public int[] bipartition;
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
		bipartition= new int[2];
		//Regular queue with O(1) operations
		Queue<Integer> agenda=new LinkedList<>();
		int source=Integer.parseInt(data[2]);
		int[] parents=new int[v];
		bipartition[0]=1;
		marked[source]=true;
		agenda.add(source);
		int color=0;
		parents[source]=0;
		//Cycle. Worst case O(v+e)
		while(!agenda.isEmpty())
		{
			source=agenda.poll();
			color=(parents[source]+1)%2;
			for(int i=0;i<v;i++)
			{
				if(adj[source][i] && !marked[i])
				{
					bipartition[color]++;
					marked[i]=true;
					agenda.add(i);
					parents[i]=color;
				}
			}
		}
		
	}
	/**
	 * Method that joins two bipartite graphs into one showing the two possible alternatives.<br>
	 * @param part1 First bipartite graph.<br>
	 * @param part2 Second bipartite graph.<br>
	 * @return rta Integer array that stores the tow possible scenarios of joining between classes.
	 */
	public static int[] joinPartition1(int[] part1, int[] part2)
	{
		int[] rta=new int[2];
		rta[0]=part1[0]+part2[0];
		rta[1]=part1[1]+part2[1];
		return rta;
	}
	/**
	 * Method that joins two bipartite graphs into one showing the two possible alternatives.<br>
	 * @param part1 First bipartite graph.<br>
	 * @param part2 Second bipartite graph.<br>
	 * @return rta Integer array that stores the tow possible scenarios of joining between classes.
	 */
	public static int[] joinPartition2(int[] part1, int[] part2)
	{
		int[] rta=new int[2];
		rta[0]=part1[1]+part2[0];
		rta[1]=part1[0]+part2[1];
		return rta;
	}
}
