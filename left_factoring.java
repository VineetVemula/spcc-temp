import java.util.*;



class left_factoring{
	
	static void remove_left_factoring(String input, char start_variable){
		int i = 3;
		String prod1 = "";
		String prod2 = "";

		while(input.charAt(i) != '|'){
			prod1 += input.charAt(i);
			i++;
		}

		i++;


		for(; i < input.length(); i++){
			prod2 += input.charAt(i);
		}

		int common_count = 0;
		for(int j=0; j < prod1.length(); j++){
			if(prod1.charAt(j) == prod2.charAt(j))
				common_count++;

			else break;
		}

		String beta = prod1.substring(common_count, prod1.length());
		String gama = prod2.substring(common_count, prod2.length());

		// System.out.println(beta);
		// System.out.println(gama);

		String new_variable = start_variable + "'";
		String factored_prod1 = start_variable + "->" + start_variable + new_variable;
		String factored_prod2 = new_variable + "->" + beta + "|" + gama;

		System.out.println(factored_prod1);
		System.out.println(factored_prod2);



	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		System.out.println("Input = " + input);

		char start_variable = input.charAt(0);
		remove_left_factoring(input, start_variable);

		
	}

}