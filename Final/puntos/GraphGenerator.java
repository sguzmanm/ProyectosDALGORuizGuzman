package puntos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class GraphGenerator {
	
	public static void main(String[] args) throws Exception {
		GraphGenerator g = new GraphGenerator();
		/*for(int i=1;i<=3;i++)
		{
			g.diez(i);
			g.cien(i);
			g.mil(i);
		}
		g.dificil();
		g.dificil2();
		g.distribuido();*/
		g.megaFile();
		
	}
	
	public void diez(int num) throws Exception
	{
		FileWriter fw = new FileWriter("./data/ProblemaB/diez"+num+".txt");
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
		FileWriter fw = new FileWriter("./data/ProblemaB/cien"+num+".txt");
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
		FileWriter fw = new FileWriter("./data/ProblemaB/mil"+num+".txt");
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
		FileWriter fw = new FileWriter("./data/ProblemaB/dificil.txt");
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
		FileWriter fw = new FileWriter("./data/ProblemaB/dificil2.txt");
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
	
	public void distribuido() throws Exception
	{
		FileWriter fw = new FileWriter("./data/ProblemaB/distribuido.txt");
		fw.write("100\n");
		String msj="";
		for(int k=0;k<100;k++)
		{
			int[]v=new int[k+1];
			msj=(k+1)+" "+(k);
			for(int i=1;i<v.length;i++)
			{
				msj+=" "+0+" "+i;
			}
			fw.write(msj+"\n");
		}
		fw.close();
	}
	
	public void megaFile() throws Exception
	{
		String s="";
		final File f =new File("./data/ProblemaB/");
		InputStream in=null;
		File d = new File("./data/ProblemaB/completo.txt");
		int i=0;
		String line="";
		for(File f1:f.listFiles())
		{
			System.out.println(f1.getAbsolutePath());
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			line=br.readLine();
			while(line!=null && line.length()>0)
			{
				s+=line+"\n";
				line=br.readLine();
			}
			br.close();
			System.out.println("ACABE "+i);
			i++;
		}
		FileWriter fw = new FileWriter(d);
		fw.write(s);
		fw.close();
		/* You can get Path from file also: file.toPath() */
	}
}

