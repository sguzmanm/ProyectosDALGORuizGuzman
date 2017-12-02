package puntos;

import java.io.FileWriter;

public class GraphGenerator {
	
	public static void main(String[] args) {
		GraphGenerator g = new GraphGenerator();
		g.diez();
	}
	public void diez()
	{
		int lim=0;
		int[]v=new int[10];
		String msj="10 9";
		for(int i=1;i<v.length;i++)
		{
			msj+=" "+(int)(Math.random()*lim)+" "+i;
		}
		System.out.println(msj);
	}
	
	public void cien()
	{
		
	}
	
	public void mil()
	{
		
	}
}

