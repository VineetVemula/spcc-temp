import java.util.*;
import java.io.*;

class st{
	static String symbols[] = new String[10];
	static int vals[] = new int[10];
	static int len[] = new int[10];
	static int counter = 0;
	static boolean is_definition(String instruction){
		String parts[] = instruction.split("\\s+");


		for(int i=0; i < parts.length; i++){
			if(parts[i].equals("DC") || parts[i].equals("DS"))
				return true; 
		}
		return false;
	}

	static int def_length(String instruction){
		String parts[] = instruction.split("\\s+");
		int val = 0;
		int lent = 0;
		if(parts[1].equals("DC")){
			if(parts[2].equals("H")){
				val = lent = 2;
			}

			if(parts[2].equals("F"))
				val = lent = 4;
		}
		else{
			if(parts[3].equals("H")){
				val =  (2 * Integer.parseInt(parts[2]));
				lent = 2;
			}

			if(parts[3].equals("F")){
				val =  (4 * Integer.parseInt(parts[2]));
				lent = 4;
			}
		}
		symbols[counter] = parts[0];
		len[counter] = lent;
		return val;
	}

	static int find_length(String instruction){
		String parts[] = instruction.split("\\s+");

		try{
			int x = Integer.parseInt(parts[2]);
			return 2;
		}catch(Exception e){
			return 4;
		}
	}


	public static void main(String args[]) throws IOException{

		FileInputStream fr = new FileInputStream("st.txt");
		DataInputStream din = new DataInputStream(fr);
		BufferedReader br = new BufferedReader(new InputStreamReader(din));



		String instructions[] = new String[30];
		int length[] = new int[30];
		length[0] = length[1] = length[2] = 0;
		String strLine;
		int c = 0;
		while((strLine = br.readLine()) != null)
			instructions[c++] = strLine;

		for(int i=2; i < c-1; i++){
			if(is_definition(instructions[i])){
				length[i+1] = length[i] + def_length(instructions[i]);
				vals[counter] = length[i];
				counter++;
				
			}
			else{
				length[i+1] = length[i] + find_length(instructions[i]);
			}

			//System.out.println(instructions[i] + " = " +find_length(instructions[i]));
		}

		// for(int i=0; i < c; i++)
		// 	System.out.println(length[i]);

		for(int i=0; i < counter; i++)
			System.out.println(symbols[i] + " " + vals[i] + " " + len[i]);




	}
}