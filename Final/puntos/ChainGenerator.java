package puntos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ChainGenerator {
	
	public static void main(String[] args) throws Exception {
		ChainGenerator.facil(1);
	}

	public static void facil(int num) throws IOException
	{
		FileWriter fw = new FileWriter("./data/ProblemaC/facil"+num+".txt");
		fw.write("10 100\n");
		String msj="";
		int lim=0;
		
	    Random random = new Random();
	    char[] word=new char[1000];
       // char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
        String chain=new String(word);
        System.out.println(word);
        fw.close();
	}
}
