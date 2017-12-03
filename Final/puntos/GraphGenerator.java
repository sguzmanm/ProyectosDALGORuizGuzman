package puntos;

import java.io.FileWriter;

public class GraphGenerator {
	
	public static void main(String[] args) throws Exception {
		GraphGenerator g = new GraphGenerator();
		for(int i=1;i<=3;i++)
		{
			g.diez(i);
			g.cien(i);
			g.mil(i);
		}
		g.dificil();
		g.dificil2();
	}
	
	public void diez(int num) throws Exception
	{
		FileWriter fw = new FileWriter("./data/PuntoB/cien"+num+".txt");
		fw.write("100\n");
		String msj="";
		int lim=0;
		for(int k=0;k<100;k++)
		{
			lim=(int)(Math.random()*10);
			if(lim==0) lim++;
			int[]v=new int[lim];
			msj=lim+" "+(lim-1);
			for(int i=1;i<v.length;i++)
			{
				msj+=" "+(int)(Math.random()*(i-1))+" "+i;
			}
			fw.write(msj+"\n");
		}
		fw.close();
		
	}
	
	public void cien(int num) throws Exception
	{
		FileWriter fw = new FileWriter("./data/PuntoB/mil"+num+".txt");
		fw.write("100\n");
		String msj="";
		int lim=0;
		for(int k=0;k<100;k++)
		{
			lim=(int)(Math.random()*100);
			if(lim==0) lim++;
			int[]v=new int[lim];
			msj=lim+" "+(lim-1);
			for(int i=1;i<v.length;i++)
			{
				msj+=" "+(int)(Math.random()*(i-1))+" "+i;
			}
			fw.write(msj+"\n");
		}
		fw.close();
	}
	
	public void mil(int num) throws Exception
	{
		FileWriter fw = new FileWriter("./data/PuntoB/diez"+num+".txt");
		fw.write("100\n");
		String msj="";
		int lim=0;
		for(int k=0;k<100;k++)
		{
			lim=(int)(Math.random()*1000);
			if(lim==0) lim++;
			int[]v=new int[lim];
			msj=lim+" "+(lim-1);
			for(int i=1;i<v.length;i++)
			{
				msj+=" "+(int)(Math.random()*(i-1))+" "+i;
			}
			fw.write(msj+"\n");
		}
		fw.close();
	}
	
	public void dificil() throws Exception
	{
		FileWriter fw = new FileWriter("./data/PuntoB/dificil.txt");
		fw.write("100\n");
		String msj="";
		int lim=1000;
		for(int k=0;k<100;k++)
		{
			int[]v=new int[1000];
			msj=lim+" "+(lim-1);
			for(int i=1;i<v.length;i++)
			{
				msj+=" "+0+" "+i;
			}
			fw.write(msj+"\n");
		}
		fw.close();
	}
	public void dificil2() throws Exception
	{
		FileWriter fw = new FileWriter("./data/PuntoB/dificil2.txt");
		fw.write("99\n");
		String msj="";
		int lim=1000;
		for(int k=0;k<99;k++)
		{
			int[]v=new int[1000];
			msj=lim+" "+(lim-1);
			for(int i=1;i<v.length;i++)
			{
				msj+=" "+0+" "+i;
			}
			fw.write(msj+"\n");
		}
		fw.close();
	}
}

