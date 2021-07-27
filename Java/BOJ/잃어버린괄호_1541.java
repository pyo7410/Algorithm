import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class 잃어버린괄호_1541 {
	public static String formula, post;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		formula = br.readLine();
		
		makePost();
		
		System.out.println(calc());
	}
	
	public static void makePost() {
		StringBuilder sb = new StringBuilder();
		Stack<Character> stk = new Stack<Character>();
		
		HashMap<Character, Integer> priority = new HashMap<Character, Integer>();
		priority.put('+', 1);
		priority.put('-', 0);
		
		int len = formula.length();
		int num = 0;
		for (int i = 0; i < len; ++i) {
			char cur = formula.charAt(i);
			if (cur >= '0' && cur <= '9') {
				num *= 10;
				num += cur - '0';
				continue;
			}
			
			sb.append(num).append("/");
			num = 0;
			
			while (!stk.empty() && priority.get(stk.peek()) >= priority.get(cur)) {
				sb.append(stk.pop());
			}
			
			stk.push(cur);
		}
		
		sb.append(num).append("/");
		
		while (!stk.empty())
	    {
	        sb.append(stk.pop());
	    }
		
		post = sb.toString();
	}
	
	public static int calc() {
		Stack<Integer> stk = new Stack<Integer>();
		
		int len = post.length();
		int num = 0;
		for (int i = 0; i < len; ++i) {
			char cur = post.charAt(i);
			if (cur >= '0' && cur <= '9') {
				num *= 10;
				num += cur - '0';
				continue;
			}
			
			if (cur == '/') {
				stk.push(num);
				num = 0;
				continue;
			}
			
			int op2 = stk.pop();
			int op1 = stk.pop();
			
			if (cur == '+') {
				stk.push(op1 + op2);
			}
			else if (cur == '-') {
				stk.push(op1 - op2);
			}
		}
		
		return stk.pop();
	}
}
