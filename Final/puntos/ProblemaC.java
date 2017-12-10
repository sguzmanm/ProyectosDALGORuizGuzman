package puntos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import sun.security.jca.GetInstance.Instance;

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
		ProblemaCGreedy greedy = new ProblemaCGreedy();
		for (int i = 0; i < 5; i++) {
			int length = greedy.textoMinimo(sb).length();
			if (length<lengthReference) lengthReference = length;
		}
		System.out.println("LENGTH REFERENCE " + lengthReference);

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
		if (solutions.isEmpty()) throw new Exception("No se encontro ninguna solucion que cumpla con la referencia de costo");
		return opt.getT();
	}

	//
	private List<CharacterChainState> findFeasibleSolutions(String sb [], int k) {
		List<CharacterChainState> answer = new ArrayList<>();
		//Estado inicial
		CharacterChainState state = new CharacterChainState("", sb, 0);
		//Agenda
		agenda.add(state);
		while(agenda.size()>0) {
			//Elige el proximo estado de la agenda
			state = agenda.poll();
			//Verifica si es viable
			if(isViable(state)) {
				//Verifica si es una solucion
				if(isSolution(state)) answer.add(state);
				//Agrega los sucesores a la agenda 
				List<CharacterChainState> successors = getSuccessors (state, k);
				agenda.addAll(successors);
			}
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
		for (int i = 0; i < state.getSb().length; i++)
		{
			String fatherT = fatherText;
			List<String> sb = new ArrayList<String>();
			int j = 0;
			int where = -1;
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
			int cantMenos = fatherText.length()-where;
			if (where == -1) cantMenos = 0;
			String t = fatherT + fatherSb[i];
			String subT = t;
			if (state.getN()>0) 
				subT = t.substring(t.length() - (2*(k-cantMenos)-1+cantMenos), t.length());
			for (int x = 0; x < fatherSb.length;x++)
			{
				if (!subT.contains(fatherSb[x])) {
					sb.add(fatherSb[x]);
				}
			}

			String [] answ = new String [sb.size()];
			answ = sb.toArray(answ);
			CharacterChainState succesor = new CharacterChainState(t, answ, state.getN()+1);
			successors.add(succesor);
		}
		return successors;
	}
	/**
	 * Determina si un estado es viable, indirectamente decide si se debe efectuar un corte de rama 
	 * @param Stado que se va a verificar 
	 * @return boolean true si el estado es viable
	 */
	private boolean isViable(CharacterChainState state) {
		return state.getT().length() <= lengthReference;
	}

	/**
	 * Determina si el estado dado es una solucion
	 * @param stado que sera verificado
	 * @return boolean true si el estado ya no tiene sub textos y su tamaño de texto es menor que la referencia
	 */
	private boolean isSolution(CharacterChainState state) {
		if (state.getSb().length == 0 && state.getT().length() <= lengthReference) {
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
			String sb [] = new String [n];
			while(line!=null && line.length()>0) {
				sb[i] = line.trim();
				line = br.readLine();
				i++;
			}

			String resp = pd.textoMinimo(sb,k);
			System.out.println(resp);
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
}



