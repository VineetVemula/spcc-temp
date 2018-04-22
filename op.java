import java.util.*;

class op{
	static boolean should_reduce(char stack, char input){
		if(stack == 'i')
			return true;

		if(stack == ('*') && input == ('i'))
			return false;

		if(stack == ('*') && input == ('+'))
			return true;

		if(stack == ('+') && input == ('*'))
			return false;

		if(stack == ('+') && input == ('&'))
			return true;

		if(stack == (input))
			return true;

		if(stack == ('$'))
			return false;

		if(input == '$')
			return true;

		return false;
	}
	public static void main(String args[]){
		String input = "i+i*i";
		String stack = "$";
		int top = 0;
		input += "$";

		while(true){
			if(stack.equals("$E") && input.equals("$")){
				break;
			}
			int ctop = top;
			while(stack.charAt(ctop) == 'E')
				ctop--;

			if(!should_reduce(stack.charAt(ctop), input.charAt(0))){
				stack += input.charAt(0);
				top++;
				input = input.substring(1, input.length());
			}
			else{
				if(stack.endsWith("i")){
					stack = stack.substring(0, stack.length() - 1) + "E";
				}
				else if(stack.endsWith("E+E") || stack.endsWith("E*E")){
					stack = stack.substring(0, stack.length() - 3) + "E";
					top -= 2;
				}


			}

			System.out.println(stack + " " + input);
		}

	}
}