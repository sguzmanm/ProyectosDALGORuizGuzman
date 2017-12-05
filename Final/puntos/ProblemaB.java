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
		//HashMap para las diferencias de los grafos. Por lo que se entiende, la complejidad de las operaciones de inserción y obtención de elementos del hash es constante.
		HashMap<Integer,List<Integer>> hash= new HashMap<>();
		//Ciclo para determinar la máxima diferencia O(N)
		//P1: 0<=i<N ^ maxDifference=(max k|0<=k<i|:graphs[k].difference)
		//t:N-i
		for(int i=0;i<graphs.length;i++)
		{
			//Diferencia temporal
			maxDifference+=graphs[i].difference;
			//Si no existe una lista de esta diferencia creéla
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
		//0<=k<hash.keySet().size() ^ (ForAll j|0<=j<k: (Exists c in hash.get(j)|0<=c<N: unico(matrix[0][i],c))
		//t2:hash.keySet().size()-k
		
		//De esta forma se garantizan los dos casos bases de la ecuación de recurrencia m. Por razones de espacio la fila 0 correspondería a usar 1 grafo,
		//la 1 2, y así.
		for(Integer i:hash.keySet())
		{
			matrix[0][i][hash.get(i).get(0)]=true;
		}
		//Itera sobre todas las filas
		boolean exists=true;
		//Ciclo por cada fila de la matriz desde una cantidad de 2 grafos o desde la fila 1
		//P3: 1<=i<N ^ s={z|0<=z<=D} ^ (ForAll i2|1<=i2<i: (ForAll j|O<=j<=D : 
		//(ForAll k|0<=k<j ^ matrix[i2][k]!=0:ParPosible(b,d,s)) ^ (ForAll k |0<=k<j ^ matrix[i2][k]==0: !ParPosible(b,d,s))))
		//t3: N-i
		//Por fuera tiene una complejidad de O(N). AL agregar su complejidad interna se tiene en total O((N*D)^2)
			for(int i=1;i<matrix.length;i++)
			{
				//P4: O<=j<=D ^ s={z|0<=z<=D} ^ (ForAll k|0<=k<j ^ matrix[i][k]!=0:ParPosible(b,d,s)) ^ (ForAll k |0<=k<j ^
				//matrix[i][k]==0: !ParPosible(b,d,s))
				//t4: D+1-j
				//Por fuera tiene una complejidad de O(D). Al agregar su complejidad interna se tiene en total O(D^2*N)
				for(int j=0;j<matrix[0].length;j++)
				{
					exists=false;
					//En total los ciclos de las invariantes P5.1 y P5.2 generan ua complejidad de O(D) por fuera.
					//Internamente se realizan dos recorridos de complejidad O(N) en el peor de los casos, y ya que debe verificar si la matriz no es el vector cero 
					//y además revisar los grafos que hacen parte de la diferencia indicada en caso de que la primera condición sea cierta. Igual en el peor de los casos
					// se topó con una diferencia que contiene todos los grafos dados por lo que se haría un segundo recorrido de O(N). Por esto se plantea una 
					//complejidad de O(N)+O(N), osea O(N)
					
					//Teniendo en cuenta la complejidad interna se genera un total de O(N*D)
			
					//P5.1: exists=ParPosible(b,d,s) ^ O<=k<j-k ^ s={z|0<=z<k:z}U{z|j-k<=z<j|z}  ^ !exists
					//t5.1: j/2-k
					for(int k=0;k<j-k && !exists;k++)
					{
						//Evalua la posibilidad de que m(i-1)(k) no sea el vector cero y de que hallan grafos con diferencia j-k
						
						if(hash.get(j-k)!=null && notZero(matrix[i-1][k]))
						{
							//Revisa los grafos con la diferencia dada
							for(Integer g:hash.get(j-k))
							{
								//Si no se ha usado el grafo se añade a un nuevo arreglo de booleanos y se rompe el ciclo.
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
						//Evalua la posibilidad de que m(i-1)(j-k) no sea el vector cero y de que hallan grafos con diferencia k
						else if(hash.get(k)!=null && notZero(matrix[i-1][j-k]))
						{
							//Revisa los grafos con la diferencia dada
							for(Integer g:hash.get(k))
							{
								if(!matrix[i-1][j-k][g])
								{
									//Si no se ha usado el grafo se añade a un nuevo arreglo de booleanos y se rompe el ciclo.
									b=Arrays.copyOf(matrix[i-1][j-k], matrix[0][0].length);
									matrix[i][j]=b;
									matrix[i][j][g]=true;
									exists=true;
									break;
								}
							}
						}
					}
					//R5.1: exists=(ParPosible(b,d,{i|0<=i<j})=(m(i,j)!=0))
					
					//P5.2: exists=ParPosible(b,d,s) ^ j<=k<=D ^ s={z|0<=z<j:z}U{z|j<=z<=k}  ^ !exists
					//t5.2: D-k				
					for(int k=j;k<matrix[0].length && !exists;k++)
					{
						//Evalua la posibilidad de que m(i-1)(k) no sea el vector cero y de que hallan grafos con diferencia k-j
						if(hash.get(k-j)!=null && notZero(matrix[i-1][k]))
						{
							//Revisa los grafos con la diferencia dada
							for(Integer g:hash.get(k-j))
							{
								//Si no se ha usado el grafo se añade a un nuevo arreglo de booleanos y se rompe el ciclo.
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
						//Evalua la posibilidad de que m(i-1)(k-j) no sea el vector cero y de que hallan grafos con diferencia k
						else if(hash.get(k)!=null && notZero(matrix[i-1][k-j]))
						{
							//Revisa los grafos con la diferencia dada
							for(Integer g:hash.get(k))
							{
								//Si no se ha usado el grafo se añade a un nuevo arreglo de booleanos y se rompe el ciclo.
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
					//R5.2: exists=(ParPosible(b,d,{i|0<=i<=D})=(m(i,j)!=0))
				}
			
			
		}
		//P6: 0<=j<D+1 ^ (ForAll k|0<=k<j: !notZero(matrix[N-1][k])
		//En otras palabras, para todas las diferencias antes de la que se está analizando, el arreglo de booleanos debe ser el
		//vector cero. Si no se retorna el valor de la diferencia buscada.
		//t6:D+1-j
		for(int j=0;j<matrix[0].length;j++)
		{
			if(notZero(matrix[matrix.length-1][j]))
			{
				//j sería la mínima diferencia alcanzable
				return j;
			}
		}
		//R6: 0<=j<D+1 ^ (ForAll k|0<=k<=D: !notZero(matrix[N-1][k])
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
