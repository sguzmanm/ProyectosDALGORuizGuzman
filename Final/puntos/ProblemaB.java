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
 * Graph class
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
		//Q: The graph is bipartite
		//Data split O(n)
		String[] data=line.split(" ");
		//Number of vertexes
		int v=Integer.parseInt(data[0]);
		//Adjacency matrix
		boolean[][] adj= new boolean[v][v];
		//P1:2<=i<data.length ^ AdjPairs(data,i,adj)
		//t1:n-i
		for(int i=2;i<data.length;i+=2)
		{
			adj[Integer.parseInt(data[i])][Integer.parseInt(data[i+1])]=true;
			adj[Integer.parseInt(data[i+1])][Integer.parseInt(data[i])]=true;
		}
		//R1: AdjPairs(data,data.length,adj)
		//Marked array for each pair until data.length there is an edge in adj
		boolean[] marked=new boolean[v];
		bipartition= new int[2];
		//Regular queue with O(1) operations
		Queue<Integer> agenda=new LinkedList<>();
		int source=Integer.parseInt(data[2]);
		//Array that stores the value of the vertixes partition, which is either 0 or 1.
		int[] value=new int[v];
		//Adds one element to the first number of the bipartition
		bipartition[0]=1;
		marked[source]=true;
		agenda.add(source);
		int color=0;
		value[source]=0;
		//Cycle with complexity O(V)
		//P2:!agenda.isEmpty() ^ bip(marked,value,0)=bipartition[0]^bip(marked,value,1)=bipartition[1]
		//t2:agenda.size()
		while(!agenda.isEmpty())
		{
			//New source of bfs
			source=agenda.poll();
			//Changes the value of the given vertex to the other possible bipartition for the children found in the bfs
			color=(value[source]+1)%2;
			//Cycle that goes through all the adjoint vertexes. O(V)
			//P3:0<=i<v ^ markedValue(adj,source,i,marked,value)
			//t3:v-i
			for(int i=0;i<v;i++)
			{
				if(adj[source][i] && !marked[i])
				{
					bipartition[color]++;
					marked[i]=true;
					agenda.add(i);
					value[i]=color;
				}
			}
			//R3:markedValue(adj,source,v,marked,value)
		}
		//R2: agenda.isEmpty() ^ bip(marked,value,0)=bipartition[0]^bip(marked,value,1)=bipartition[1] ^ generalMarked(adj,v,marked,value)
		
	}
}
