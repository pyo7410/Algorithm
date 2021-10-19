import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 후위표기식_1918 {
	public static String input;
	public static Stack<Character> stk;
	public static Map<Character, Integer> priority, innerPriority;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		input = br.readLine();
		
		priority = new HashMap<>();
		innerPriority = new HashMap<>();
		
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
		// '('를 넣을 때는 무조건 스택에 들어가야하므로
		// 우선순위를 제일 높게 해야한다.
		priority.put('(', 3);
		
		innerPriority.put('+', 1);
		innerPriority.put('-', 1);
		innerPriority.put('*', 2);
		innerPriority.put('/', 2);
		// 괄호 안의 연산자들도 정상적으로 스택에 넣어야 하므로
		// 스택 내부에서는 '('의 우선순위가 제일 낮게 해야한다.
		innerPriority.put('(', 0);		
		
		int len = input.length();
		
		stk = new Stack<>();
		for (int i = 0; i < len; ++i) {
			char cur = input.charAt(i);
			
			if (cur >= 'A' && cur <= 'Z') {
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
		
		System.out.println(sb);
	}
}
