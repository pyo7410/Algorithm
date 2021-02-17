package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class 계산기1_1222 {
	public static int N;
	public static String input, formula;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			input = br.readLine();
			
			makePost();
			
			sb.append("#").append(tc).append(" ").append(calc()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void makePost() {
		Stack<Character> stk = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		HashMap<Character, Integer> priority = new HashMap<Character, Integer>();
		priority.put('+', 0);
		
		int len = input.length();
		for (int i = 0; i < len; ++i) {
			char cur = input.charAt(i);
			if (cur >= '0' && cur <= '9') {
				sb.append(cur);
				continue;
			}
			
			while (!stk.isEmpty() && priority.get(stk.peek()) >= priority.get(cur)) {
				sb.append(stk.pop());
			}
			
			stk.push(cur);
		}
		
		while (!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		
		formula = sb.toString();
	}
	
	public static int calc() {
		Stack<Integer> stk = new Stack<Integer>();
		
		for (int i = 0; i < formula.length(); ++i) {
			char cur = formula.charAt(i);
			
			if (cur >= '0' && cur <= '9') {
				stk.push(cur - '0');
				continue;
			}
			
			if (cur == '+') {
				int op2 = stk.pop();
				int op1 = stk.pop();
				
				stk.push(op1 + op2);
			}
		}
		
		return stk.pop();
	}
}
