import java.util.Stack;

// TODO expand to work with generic types and supplied ops
public class ShuntingYardAlg {
	
	/**
	 * Evaluates the expression.
	 * @param expr expression which is being evaluated. It needs to end with a ')'
	 * in order to evaluate correctly.
	 * @return value of the expression.
	 */
	public int evaluate(String expr) {
		
		Stack<Integer> vals = new Stack<>();	// operator stack
		Stack<Character> ops = new Stack<Character>();	// operations stack
		
		
		char c;
		int first, second;	// operator variables
		for (int i = 0; i < expr.length(); i++) {	// iterates through the string, char by char
		    c = expr.charAt(i);   
		    
		   if (Character.isDigit(c))
			   vals.push(Character.getNumericValue(c));	// pushes the operator onto the stack
		   else if (c == '+' || c == '-' || c == '*' || c == '/') 
			   ops.push(c);	// pushes the operation onto the stack
		   else if (c == ')') {
			   
			   second = vals.pop();
			   first = vals.pop();
			   
			   switch (ops.pop()) {
				   case '+': vals.push(first + second); break;
				   case '-': vals.push(first - second); break;
				   case '*': vals.push(first * second); break;
				   case '/': vals.push(first / second); break;
			   }
		   }
		   
		   System.out.println("The vals stack: " + vals);
		   System.out.println("The ops stack: " + ops + "\n");
		   
		}
		
		
		return vals.pop();
	}

	/**
	 * Tests the shunting yard algorithm.
	 * @param args currently not in use.
	 */
	public static void main(String[] args) {
		ShuntingYardAlg test = new ShuntingYardAlg();
		
		String expression = "(1+((2+3)*(4*5)))";	// result is 101
		System.out.println(expression + " = " + test.evaluate(expression));
		
		expression = "((((6ovo nece smetati, bar nebi trebalo lol+4)*3)-2)+7)";	// result is 35
		System.out.println(expression + " = " + test.evaluate(expression));
	}

}
