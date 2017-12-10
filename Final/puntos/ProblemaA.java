package puntos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
/**
 * 
 * @author Sergio Guzmán- Juan Camilo Ruiz
 *
 */
public class ProblemaA {

	public double problema1 (int n,double A, double B,double C,double D) {
		
		//Q: true
		double [] reserva = new double [n+1];
		reserva[0] = A;
		reserva[1] = B;

		//P1: 2 <= i <= N ^ (ForAll k| 0 <= k <= i : reserva[k] = r(k))
		//t1: N - i
		for (int i = 2; i <= n; i++) {
			reserva[i] = C*reserva[i-2] + D*reserva[i-1];
		}
		//R1: ForAll k| 0 <= k <= N : reserva[k] = r(k)
		
		double cp = 0;
		
		//P2: 0 <= i <= N/2 ^ cp = cp(r,i)
		//t2: N/2 - i
		for (int i=0; i <= n-i; i++) {
			cp += reserva[i]*reserva[n-i];
		}
		//R2: cp = cp(r, n/2)
		
		cp = cp*2;
		
		//Q2: cp = cp(r,N) + max(0,((n+1)%2)*(N/2)^2)
		if (n%2==0) {
			cp -= reserva[n/2]*reserva[n/2];
		}
		//R3: cp = cp(r,N)
		return redondear(cp);
	}

	public double redondear(double numero) {

		BigDecimal bd = new BigDecimal(numero);
		bd = bd.setScale(4, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static void main(String[] args) throws Exception{
		//Instancia del problema
		ProblemaA pd = new ProblemaA();
		ArrayList<Double> resp = new ArrayList<>();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();
			while(line!=null && line.length()>0) {
				String valores [] = line.split(" ");
				if (valores.length != 5) throw new Exception("Valores ingresados errados");
				resp.add(pd.problema1(Integer.parseInt(valores[0]), Double.parseDouble(valores[1]), Double.parseDouble(valores[2]), Double.parseDouble(valores[3]), Double.parseDouble(valores[4])));
				line=br.readLine();
			}
			//If no one is typing anymore end the algorithm and show the answers
			for(Double r:resp)
				System.out.println(r);

		}

	}
}
