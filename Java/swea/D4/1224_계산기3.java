package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class 계산기3_1224 {
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
		HashMap<Character, Integer> innerPriority = new HashMap<Character, Integer>();
		priority.put('+', 1);
		priority.put('*', 2);
		// '('를 넣을 때는 무조건 스택에 들어가야하므로
		// 우선순위를 제일 높게 해야한다.
		priority.put('(', 3);
		
		innerPriority.put('+', 1);
		innerPriority.put('*', 2);
		// 괄호 안의 연산자들도 정상적으로 스택에 넣어야 하므로
		// 스택 내부에서는 '('의 우선순위가 제일 낮게 해야한다.
		innerPriority.put('(', 0);		
		
		int len = input.length();
		
		for (int i = 0; i < len; ++i) {
			char cur = input.charAt(i);
			
			if (cur >= '0' && cur <= '9') {
				sb.append(cur);
				continue;
			}
			
			if (cur == ')') {
				while (!stk.isEmpty() && stk.peek() != '(') {
					sb.append(stk.pop());
				}
				
				// ')' 제거
				if (!stk.isEmpty()) {
					stk.pop();					
				}
				continue;
			}
			
			while (!stk.isEmpty() && innerPriority.get(stk.peek()) >= priority.get(cur)) {
				sb.append(stk.pop());
			}
			
			stk.push(cur);
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
			
			int op2 = stk.pop();
			int op1 = stk.pop();
			
			if (cur == '+') {
				stk.push(op1 + op2);
			}
			else if (cur == '*') {
				stk.push(op1 * op2);
			}
		}
		
		return stk.pop();
	}
}
