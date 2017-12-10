package puntos;

import java.util.ArrayList;
import java.util.List;

public class ProblemaCGreedy {

	//Elige aleatoriamente subtextos del arreglo sb cada vez, calculando una posible solucion de forma aleatoria
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

	public static void main(String[] args) throws Exception {
		ProblemaCGreedy pc = new ProblemaCGreedy();
		String [] sb = new String [6];
		sb[0] = "nfid";
		sb[1] = "conf";
		sb[2] = "cial"; 
		sb[3] = "denc";
		sb[4] = "onfi"; 
		sb[5] = "enci";
		System.out.println("LA CADENA DE MENOR TAMAÑO ES ------> " + pc.textoMinimo(sb));
		System.out.println(sb[0] +sb[1] +sb[2] +sb[3] +sb[4] +sb[5]);
	}
}
