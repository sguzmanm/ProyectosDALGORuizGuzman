package puntos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Sergio Guzmán- Juan Camilo Ruiz
 *
 */
public class ProblemaB {

	private String toString(boolean[]b)
	{
		String msj="";
		for(int i=0;i<b.length;i++)
		{
			if(b[i]) msj+="1 ";
			else msj+="0 ";
		}
		return msj;
	}
	private int minimumDifference(Graph[] graphs)
	{
		//Variables of max difference and temporal difference
		int maxDifference=0;
		int dif=0;
		//Hash for the graph differences
		HashMap<Integer,List<Integer>> hash= new HashMap<>();
		//Cycle to determine max
		//P1: Until i the variable in maxDifference is the biggest
		//t:n-i
		for(int i=0;i<graphs.length;i++)
		{
			dif=graphs[i].difference;
			if(dif>maxDifference)
			{
				maxDifference=dif;
			}
			if(!hash.containsKey(dif))
			{
				hash.put(dif, new ArrayList<>());
			}
			hash.get(dif).add(i);
		}
		//Variable for boolean array
		boolean[] b=null;
		//Max difference holds the biggest difference in numbers
		boolean[][][] matrix = new boolean[graphs.length][maxDifference+1][graphs.length];
		//Definition of unique function for row 1
		//Iterates only over the differences
		for(Integer i:hash.keySet())
		{
			matrix[0][i][hash.get(i).get(0)]=true;
		}
		//Iterates over the other rows
		boolean exists=true;
		if(matrix.length>1)
		{
			for(int i=1;i<matrix.length;i++)
			{
				for(int j=0;j<matrix[0].length;j++)
				{
					exists=false;
					for(int k=0;k<j-k && !exists;k++)
					{
						if(notZero(matrix[i-1][k]) && hash.get(j-k)!=null)
						{
							for(Integer g:hash.get(j-k))
							{
								if(!matrix[i-1][k][g])
								{
									matrix[i][j]=matrix[i-1][k];
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
						else if(notZero(matrix[i-1][j-k]) && hash.get(k)!=null)
						{
							for(Integer g:hash.get(k))
							{
								if(!matrix[i-1][j-k][g])
								{
									matrix[i][j]=matrix[i-1][j-k];
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
					}
					for(int k=j;k<matrix[0].length && !exists;k++)
					{
						if(notZero(matrix[i-1][k]) && hash.get(k-j)!=null)
						{
							for(Integer g:hash.get(k-j))
							{
								if(!matrix[i-1][k][g])
								{
									matrix[i][j]=matrix[i-1][k];
									matrix[i][j][k-j]=true;
									exists=true;
									break;
								}
							}
						}
						else if(notZero(matrix[i-1][k-j]) && hash.get(k)!=null)
						{
							for(Integer g:hash.get(k))
							{
								if(!matrix[i-1][k+j][g])
								{
									matrix[i][j]=matrix[i-1][k-j];
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
					}
				}
			}
			
		}
		for(int j=0;j<matrix[0].length;j++)
		{
			if(notZero(matrix[matrix.length-1][j]))
			{
				return j;
			}
		}
		//If nothing can be achieved
		return -1;
	}
	/**
	 * 2
		5 4 0 1 0 2 0 3 2
		4
		4 3 0 1 1 2 2 3
	 * @param bs
	 * @return
	 */
	
	private boolean notZero(boolean[] bs) {
		for(int i=0;i<bs.length;i++)
		{
			if(bs[i]) return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception{
		ProblemaB instancia = new ProblemaB();
		List<Integer> respuestas=new ArrayList<>();
		try ( 
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
		) { 
			String line = br.readLine();
			Integer n=null;
			Graph[] graphs=null;
			int i=0;
			while(line!=null && line.length()>0) {
				if(n==null)
				{
					i=0;
					n=Integer.parseInt(line);
					graphs=new Graph[n];
				}
				else
				{
					graphs[i]=new Graph(line);
					i++;
					if(i==n)
					{
						n=null;
						respuestas.add(instancia.minimumDifference(graphs));
					}
				}
				line=br.readLine();
				
				
			}
			for(Integer r:respuestas)
				System.out.println(r);
			
		}
		
	}
}

/**
 * Graph class
 */
class Graph
{
	//Array for the boolean bipartition
	public int difference;
	/**
	 * Graph constructor that uses a given line with the structure (v e x1 y1 x1 y2... xk yk) to create a graph bipartition.<br>
	 * @param line Given line with the forma described above
	 */
	public Graph(String line)
	{
		int[] bipartition;
		//Q: The graph is bipartite
		//Data split O(n)
		String[] data=line.split(" ");
		//Number of vertexes
		int v=Integer.parseInt(data[0]);
		//Adjacency matrix
		boolean[][] adj= new boolean[v][v];
		//P1:2<=i<data.length ^ 0<=e<Integer.parseInt(data[1]) ^ AdjPairs(data,i,adj)
		//t1:data.length-e
		for(int i=2,e=0;i<data.length && e<Integer.parseInt(data[1]);i+=2)
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
		int source=0;
		if(data.length>2)
			source=Integer.parseInt(data[2]);
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
		difference=Math.abs(bipartition[1]-bipartition[0]);
	}
}
