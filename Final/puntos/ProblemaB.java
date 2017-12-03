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

	/**
	 *Retorna la diferencia mínima de haber sumado los grafos BC dados en el arreglo.<br>
	 * @param graphs Arreglo con grafos que solo poseen la diferencia como atributo.<br>
	 * @return Diferencia mínima de la suma de los grafos BC. En el caso de que el algoritmo no resuleva el problema, retorna -1.
	 */
	private int minimumDifference(Graph[] graphs)
	{
		//Variables de máxima diferencia y de diferencia temporal
		int maxDifference=0;
		int dif=0;
		//HashMap para las diferencias de los grafos. Por lo que se entiende, la complejidad de insertar y sacar un elemento es de O(log (n/m)) 
		//Donde n es el número total de elementos y m la cantidad de biparticiones que se hacen dentro del hashmap. Dicho n siempre será igual al número
		//N de grafos for lo que la complejidad de inserción se despreciará en este algoritmo al depender de m, es decir, se asume como constante.
		HashMap<Integer,List<Integer>> hash= new HashMap<>();
		//Ciclo para determinar la máxima diferencia O(N)
		//P1: 0<=i<N ^ maxDifference=(max k|0<=k<i|:graphs[k].difference)
		//t:N-i
		for(int i=0;i<graphs.length;i++)
		{
			//Diferencia temporal
			dif=graphs[i].difference;
			if(dif>maxDifference)
			{
				maxDifference=dif;
			}
			//Si no eciste una lista de esta diferencia creéla
			if(!hash.containsKey(dif))
			{
				hash.put(dif, new ArrayList<>());
			}
			//Añada a la diferencia un nuevo vértice i
			hash.get(dif).add(i);
		}
		//R1: maxDifference=(max k|o<=k<N|:graphs[k])
		//Variable para el arreglo de booleanos
		boolean[] b=null;
		//Se crea la estructura de la ecuación de recurrencia m usando el número toal de grafos N (graphs.length), la diferencia máxima D
		//(maxDifference+1, para tener en cuemta una diferencia de cero) y una vez más el número total N para registrar en un arreglo de
		//booleanos que grafos se están usando y cuáles no.
		boolean[][][] matrix = new boolean[graphs.length][maxDifference+1][graphs.length];
		//Itera únicamente sobre los elementos con diferencias registradas. En el peor de los casos se pasa nuevamente por el número total 
		//de grafos se asume complejidad O(N).
		//P2:Asumiendo que se un conjunto se puede representar como un arreglo y que los valores i son dados en forma secuencial. Se tiene la siguiente invariante:
		//0<=i<hash.keySet().size() ^ (ForAll j|0<=j<i: (Exists c in hash.get(j)|0<=c<N: unico(matrix[0][i],c))
		for(Integer i:hash.keySet())
		{
			matrix[0][i][hash.get(i).get(0)]=true;
		}
		//Itera sobre todas las filas
		boolean exists=true;
			for(int i=1;i<matrix.length;i++)
			{
				for(int j=0;j<matrix[0].length;j++)
				{
					exists=false;
					for(int k=0;k<j-k && !exists;k++)
					{
						if(hash.get(j-k)!=null && notZero(matrix[i-1][k]))
						{
							for(Integer g:hash.get(j-k))
							{
								if(!matrix[i-1][k][g])
								{
									b=Arrays.copyOf(matrix[i-1][k], matrix[0][0].length);
									matrix[i][j]=b;
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
						else if(hash.get(k)!=null && notZero(matrix[i-1][j-k]))
						{
							for(Integer g:hash.get(k))
							{
								if(!matrix[i-1][j-k][g])
								{
									b=Arrays.copyOf(matrix[i-1][j-k], matrix[0][0].length);
									matrix[i][j]=b;
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
					}
					for(int k=j;k<matrix[0].length && !exists;k++)
					{
						if(hash.get(k-j)!=null && notZero(matrix[i-1][k]))
						{
							for(Integer g:hash.get(k-j))
							{
								if(!matrix[i-1][k][g])
								{
									b=Arrays.copyOf(matrix[i-1][k], matrix[0][0].length);
									matrix[i][j]=b;
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
						else if(hash.get(k-j)!=null && notZero(matrix[i-1][k-j]))
						{
							for(Integer g:hash.get(k))
							{
								if(!matrix[i-1][k-j][g])
								{
									b=Arrays.copyOf(matrix[i-1][k-j], matrix[0][0].length);
									matrix[i][j]=b;
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
					}
				}
			
			
		}
		//P: 0<=j<matrix[0].length ^ (ForAll k|0<=k<j: !notZero(matrix[matrix.length-1][k])
		//En otras palabras, para todas las diferencias antes de la que se está analizando, el arreglo de booleanos debe ser el
		//vector cero. Si no se retorna el valor de la diferencia buscada.
		//t:matrix[0].length-j
		for(int j=0;j<matrix[0].length;j++)
		{
			if(notZero(matrix[matrix.length-1][j]))
			{
				//j sería la mínima diferencia alcanzable
				return j;
			}
		}
		//En el caso improbable de que el algoritmo no encuentre alguna diferencia alcanzable con los datos dados retorna -1
		return -1;
	}
	/**
	 * Verifica que el arreglo dado no es el vector cero.<br>
	 * @param bs Arreglo de booleanos.<br>
	 * @return Si el arreglo es el vector cero o no.
	 */
	private boolean notZero(boolean[] bs) {
		//P: 0<=i<bs.length ^ (ForAll j|0<=j<i: !bs[j])
		//t:bs.length-i
		for(int i=0;i<bs.length;i++)
		{
			if(bs[i]) return true;
		}
		//R:0<=i<bs.length ^(ForAll j|0<=j<bs.length: !bs[j])
		return false;
	}

	public static void main(String[] args) throws Exception{
		//Instancia del problema
		ProblemaB instance = new ProblemaB();
		List<Integer> answers=new ArrayList<>();
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
						answers.add(instance.minimumDifference(graphs));
					}
				}
				line=br.readLine();
			}
			//If no one is typing anymore end the algorithm and show the answers
			for(Integer r:answers)
				System.out.println(r);
			
		}
		
	}
}

/**
 * Clase del grafo
 */
class Graph
{
	//Diferencia de la bipartición
	public int difference;
	/**
	 * Constructor del grafo que usa una línea con la estructura (v e x1 y1 x1 y2... xk yk) para crear una bipartición del mismo.<br>
	 * @param line Línea con la estructura descrita arriba
	 */
	public Graph(String line)
	{
		int[] bipartition;
		//Q: El grafo es bipartito.
		//Separar los datos O(n)
		String[] data=line.split(" ");
		//Número de vértices
		int v=Integer.parseInt(data[0]);
		//Matriz de adyacencia
		boolean[][] adj= new boolean[v][v];
		//Número de aristas
		int e=Integer.parseInt(data[1]);
		//P1:2<=i<data.length ^ 0<=e<Integer.parseInt(data[1]) ^ AdjPairs(data,i,adj)
		//t1:data.length-e
		for(int i=2,j=0;i<data.length && j<e;i+=2,j++)
		{
			adj[Integer.parseInt(data[i])][Integer.parseInt(data[i+1])]=true;
			adj[Integer.parseInt(data[i+1])][Integer.parseInt(data[i])]=true;
		}
		//R1: AdjPairs(data,data.length,adj)
		//Arreglo de marcados para hacer el BFS
		boolean[] marked=new boolean[v];
		bipartition= new int[2];
		//Cola regular con operaciones en tiempo O(1)
		Queue<Integer> agenda=new LinkedList<>();
		//Por defecto la fuente es el vértice 0. Al ser conexo debe poder llegar a todos los otros.
		int source=0;
		//Arreglo que guarda el valor de la bipartición asiganda de cada vértice
		int[] value=new int[v];
		//Añade la fuente a la primera bipartición
		bipartition[0]=1;
		marked[source]=true;
		agenda.add(source);
		int color=0;
		value[source]=0;
		//Peor caso del ciclo total: O(V^2)
		//Al ser un grafo conectado, la agenda tendra como máximo todos los vértices del grafo (V) 
		//P2:!agenda.isEmpty() ^ bip(marked,value,0)=bipartition[0]^bip(marked,value,1)=bipartition[1]
		//t2:agenda.size()
		while(!agenda.isEmpty())
		{
			//Nueva fuente del BFS
			source=agenda.poll();
			//Cambia el color del vértice a la siguiente bipartición para asignársela a sus hijos
			color=(value[source]+1)%2;
			//Ciclo que va por una fila completa de la matriz de adjuntos (O(V))
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
		//Se guarda la diferencia de las dos biparticiones
		difference=Math.abs(bipartition[1]-bipartition[0]);
	}
}
