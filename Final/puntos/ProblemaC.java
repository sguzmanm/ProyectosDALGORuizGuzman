package puntos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * 
 * @author Sergio Guzmán- Juan Camilo Ruiz
 *
 */
public class ProblemaC {
	//Java´s default PriorityQueue. It acts as a MinHeap with O(log n) operations for inserting and removing elements
	private PriorityQueue<CharacterChainState> agenda= new PriorityQueue<>();

	//Referencia para la decision del problema
	private int lengthReference;

	public String textoMinimo(String sb [], int k) throws Exception {

		//Se efectua un greedy que selecciona aleatoriamente una solucion, se hace 5 veces para optener el menor valor ya que este 
		//no da siempre igual
		lengthReference = Integer.MAX_VALUE;
		String answGreedy = "";
		String greedyT = "";
		ProblemaCGreedy greedy = new ProblemaCGreedy();
		for (int i = 0; i < 5; i++) {
			answGreedy = greedy.textoMinimo(sb);
			if (answGreedy.length()<lengthReference) {
				lengthReference = answGreedy.length();
				greedyT = answGreedy;
			}
		}
		//Llama el algoritmo de exploracion de grafos para encontar las soluciones
		List<CharacterChainState> solutions = findFeasibleSolutions(sb, k);
		//Elige la mejor de las soluciones 
		CharacterChainState opt = null;
		int menorCosto = Integer.MAX_VALUE;
		for(CharacterChainState sol:solutions) {
			int costo = getLength(sol);
			if(opt == null || menorCosto > costo) {
				menorCosto = costo;
				opt = sol;
			}
		}
		if (solutions.isEmpty()) return greedyT;
		return opt.getT();
	}

	//Busca todas las posibles soluciones
	private List<CharacterChainState> findFeasibleSolutions(String sb [], int k) {
		List<CharacterChainState> answer = new ArrayList<>();
		//Estado inicial
		CharacterChainState state = new CharacterChainState("", sb, 0);
		//Agenda
		agenda.add(state);
		// P1: (estadoInicial.text = empty) ^ (estadoInicial.subtexts = sb) ^ (estadoInicial.N = 0) ^ (estadoInicial BELONGS agenda) ^ answer.isEmpty()
		//En otras palabras, el estado inicial debe poseer el texto vacio, todos los subtextos recibidos en la entrada, y su nivel debe 
		//ser 0, además, este tiene que estar en la agenda
		while(agenda.size()>0) {
			//Elige el proximo estado de la agenda
			state = agenda.poll();
				//Verifica si es una solucion
				if(isSolution(state)) answer.add(state);
				//Agrega los sucesores a la agenda 
				List<CharacterChainState> successors = getSuccessors (state, k);
				agenda.addAll(successors);
		//R1: agenda.isEmpty ^ (ForAll state | isSolution(state) : state BELONGS answer)
		// Al final de este while, la agenda debe estar vacía y todos los estados que sean solucion deben estar en el arrayList answer.
		}
		return answer;
	}

	/**
	 * Calcula los sucesores del estado que llega por parametro
	 * @param el estado al que se le calcularán los sucesores
	 * @return List<CharacterChainState> Sucesores del estado recibido
	 */
	private List<CharacterChainState> getSuccessors(CharacterChainState state, int k) {
		List<CharacterChainState> successors = new ArrayList<>();
		String fatherText = state.getT();
		String [] fatherSb = state.getSb();
		
		//Recorre todos los subtextos del estado que llega por parametro, cada vez le agrega uno de los subextos al texto, y busca si se
		//creo alguno de los otros subtextos para saber cuales son los subtextos del sucesor.
		for (int i = 0; i < state.getSb().length; i++)
		{
			String fatherT = fatherText;
			List<String> sb = new ArrayList<String>();
			int j = 0;
			int where = -1;
			//recorre las ultimas posiciones (fatherT.length - k -1) del texto, para ver si puede usar algunos caracteres del texto para crear el subtexto, minimizando
			//el tamaño del texto
			for (int y = fatherT.length()-(k - 1); y < fatherT.length() && y>=0; y++) {
				if (fatherT.charAt(y) == fatherSb[i].charAt(j)) {
					j++;
					if (where == -1) 
						where = y;
					if (y+1 == fatherT.length()) {
						if (where == -1) where = 0;
						fatherT = fatherT.substring(0, where);
					}
				}
				else if (where != -1) {
					j = 0;
					y = y-1;
					where = -1;
				}
			}
			//la cantidad de caracteres del texto que tiene el subtexto 
			int cantMenos = fatherText.length()-where;
			if (where == -1) cantMenos = 0;
			//se concatena el texto del estado padre con el subtexto correspondiente
			String t = fatherT + fatherSb[i];
			String subT = t;
			//Si el nivel del estado padre es mayor que 0, coge la subcadena de las ultimas 2k+cantMenos para verificar cuales subtextos se
			//crearon al aumentar la cadena de texto
			if (state.getN()>0) 
				subT = t.substring(t.length() - (2*(k-cantMenos)-1+cantMenos), t.length());
			//verifica que estados se crearon en el nuevo texto
			for (int x = 0; x < fatherSb.length;x++)
			{
				if (!subT.contains(fatherSb[x])) {
					sb.add(fatherSb[x]);
				}
			}

			String [] answ = new String [sb.size()];
			answ = sb.toArray(answ);
			//se crea el sucesor con el texto calculado, los subtextos calculados, y el nivel es el mismo del estado padre+1
			CharacterChainState succesor = new CharacterChainState(t, answ, state.getN()+1);
			//Se verifica de una vez si el sucesor es viable, si lo es se agrega a los sucesores si no se descarta de una vez
			if (isViable(succesor)) successors.add(succesor);
		}
		return successors;
	}
	/**
	 * Determina si un estado es viable, indirectamente decide si se debe efectuar un corte de rama 
	 * @param Stado que se va a verificar 
	 * @return boolean true si el estado es viable
	 */
	private boolean isViable(CharacterChainState state) {
		return state.getT().length() < lengthReference;
	}

	/**
	 * Determina si el estado dado es una solucion
	 * @param state estado que sera verificado
	 * @return boolean true si el estado ya no tiene sub textos y su tamaño de texto es menor que la referencia
	 */
	private boolean isSolution(CharacterChainState state) {
		if (state.getSb().length == 0 && state.getT().length() < lengthReference) {
			lengthReference = state.getT().length();
			return true;
		}
		return false;
	}

	/**
	 * Calcula el tamaño del texto del estado
	 * @param state 
	 * @return
	 */
	private int getLength(CharacterChainState state) {
		return state.getT().length();
	}

	public static void main(String[] args) throws Exception {
		ProblemaC pd = new ProblemaC();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();
			String valores [] = line.split(" ");
			int n = Integer.parseInt(valores[0]);
			int k = Integer.parseInt(valores[1]);
			line = br.readLine();
			int i = 0;
			ArrayList<String> list=new ArrayList<>();
			String sb [] = new String [n];
			while(line!=null && line.length()>0) {
				if(n==-1)
				{
					valores=line.split(" ");
					n=Integer.parseInt(valores[0]);
					k=Integer.parseInt(valores[1]);
					sb=new String[n];
				}
				else
				{
					sb[i] = line.trim();
					i++;
					if(i==n)
					{
						list.add(pd.textoMinimo(sb,k));
						i=0;
						n=-1;
					}
				}
				line = br.readLine();
				
			}
			for(String resp:list)
				System.out.println(resp);
		}
	}
	


	 class ProblemaCGreedy {

		//Elige aleatoriamente subtextos del arreglo sb cada vez, calculando una posible solucion de forma aleatoria, usa la misma logica 
		 //que el getSucessors()
		public String textoMinimo(String substext []) throws Exception {
			String fatherText = "";
			String[] fatherSb = substext;
			while (fatherSb.length > 0)
			{
				List<String> sb = new ArrayList<String>();
				int j = 0;
				int where = -1;
				int i = (int) Math.floor(Math.random() * (fatherSb.length-1));
				for (int y = fatherText.length()-(fatherSb[i].length() - 1); y < fatherText.length() && y>=0; y++) {
					if (fatherText.charAt(y) == fatherSb[i].charAt(j)) {
						j++;
						if (where == -1) 
							where = y;
						if (y+1 == fatherText.length()) {
							if (where == -1) where = 0;
							fatherText = fatherText.substring(0, where);
						}
					}
					else if (where != -1) {
						j = 0;
						y = y-1;
						where = -1;
					}
				}
				int cantMenos = fatherText.length()-where;
				if (where == -1) cantMenos = 0;
				String t = fatherText + fatherSb[i];
				String subT = t;
				if (fatherText.length()>fatherSb[i].length()) 
					subT = t.substring(t.length() - (2*(fatherSb[i].length()-cantMenos)-1+cantMenos), t.length());
				for (int k = 0; k < fatherSb.length;k++)
				{
					if (!subT.contains(fatherSb[k])) {
						sb.add(fatherSb[k]);
					}
				}
				String [] answ = new String [sb.size()];
				answ = sb.toArray(answ);
				fatherText = t;
				fatherSb = answ;
			}
			return fatherText;
		}

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

		public String getT() {
			return t;
		}



		public void setT(String t) {
			this.t = t;
		}



		public String[] getSb() {
			return sb;
		}



		public void setSb(String[] sb) {
			this.sb = sb;
		}



		public int getN() {
			return n;
		}



		public void setN(int n) {
			this.n = n;
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
				//This is the worst case for the conditional because it involves two operations.
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
}



