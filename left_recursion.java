import java.util.*;



class left_recursion{
	static String find_alpha(String input){
		String alpha = "";
		int i=4;
		while(input.charAt(i) != '|'){
			alpha += input.charAt(i);
			i++;
		}
		return alpha;
	
	}

	static String find_beta(String input){
		String beta = "";
		int i=0;
		while(input.charAt(i) != '|')
			i++;
		
		i++;
		for(; i < input.length(); i++)
			beta += input.charAt(i);
		
		return beta;
	
	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		System.out.println("Input = " + input);

		char start_variable = input.charAt(0);
		String new_variable = start_variable + "'";

		String alpha = find_alpha(input);
		System.out.println("Alpha = " + alpha);

		String beta = find_beta(input);
		System.out.println("Beta = " + beta);

		String prod1 = start_variable + "->" + beta + new_variable;
		String prod2 = new_variable + "->" + alpha + new_variable + "|e";

		System.out.println(prod1);
		System.out.println(prod2);

		

		
	}

}