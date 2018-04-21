import java.io.*;
import java.util.*;

class x{
	static int is_macro_call(String macros[], String inst){
		for(int i=0; i < macros.length && macros[i] != null; i++)
			if(macros[i].equals(inst))
				return i;

		return -1;
	}
	public static void main(String args[]) throws IOException{
		int MNT_index[] = new int[20];
		String MNT_name[] = new String[20];
		int MDT_index[] = new int[20];
		String MDT_def[][] = new String[20][20];
		String extended_alp[] = new String[50];

		int macro_def_line[] = new int[20];

		int mnt_index_i = 0;
		int mdt_index_i = 1;
		try{
			Scanner sc = new Scanner(System.in);
			FileInputStream fstream = new FileInputStream("alp.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			int c = -1;
			String instructions[] = new String[30];
			while ((strLine = br.readLine()) != null) {
				instructions[++c] = strLine;	
			}

			boolean flag = false;
			for(int i=0; i < c; i++){	
				//System.out.println(instructions[i]);
				if((instructions[i]).equals("MACRO")){
					flag = true;
					MNT_name[mnt_index_i] = (instructions[i+1]).split("\\s")[0];
					MNT_index[mnt_index_i] = ++mnt_index_i;
					MDT_index[mnt_index_i-1] = mdt_index_i;
					macro_def_line[mnt_index_i-1] = i;

					//mdt_index_i--;
				}
				if(!instructions[i].equals("MEND") && flag){
					if(!instructions[i].equals("MACRO"))
						mdt_index_i++;
				}
				else
					flag = false;
			}
			MDT_index[0] = 1;
			for(int i=0; i < mnt_index_i; i++){
				int t = 0;
				int j = macro_def_line[i] + 2;
				while(!instructions[j].equals("MEND")){
					MDT_def[i][t++] = instructions[j];
					j++;
				}
				MDT_def[i][t++] = "MEND";			
			}

			for(int i=0; i < mnt_index_i; i++){
				for(int j=0; j < MDT_def[i].length && MDT_def[i][j] != null; j++)
					System.out.println(MDT_def[i][j] + " ");

				System.out.println();
			}

			for(int i=0; i < mnt_index_i; i++)
			  	System.out.println(MNT_index[i] + " " + MNT_name[i] + " " + MDT_index[i]);

			int t = 0;
			boolean main_prog = false;
			for(int i=0; i <= c; i++){
				if(instructions[i].equals("START")){
					main_prog = true;
				}
				if(main_prog){
					int res = is_macro_call(MNT_name, (instructions[i]).split("\\s")[0]);
					if(res > -1){
						for(int j=0; !MDT_def[res][j].equals("MEND"); j++)
							extended_alp[t++] = MDT_def[res][j];
					}
					else{
						extended_alp[t++] = instructions[i];
					}
				}
			}  
			System.out.println("Extended alp is ");
			for(int i=0; i < t; i++)
				System.out.println(extended_alp[i]);

			in.close();
			  
		}	
        catch (Exception e){
  			System.err.println("Error: " + e);
 	 }
		
	}
}