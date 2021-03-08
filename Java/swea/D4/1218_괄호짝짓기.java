package D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호짝짓기_1218 {
	public static char[] brackets;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		Stack<Character> stk = new Stack<Character>();
		
		for (int tc = 1; tc <= 10; ++tc) {
			int N = Integer.parseInt(br.readLine());
			
			brackets = new char[N];
			brackets = br.readLine().toCharArray();
			stk.push(brackets[0]);
			
			int answer = 1;
			for (int i = 1; i < N; ++i) {
				if (brackets[i] == ')') {
					if (stk.peek() != '(') {
						answer = 0;
						break;
					}
					else {
						stk.pop();
						continue;
					}
				}
				else if (brackets[i] == ']') {
					if (stk.peek() != '[') {
						answer = 0;
						break;
					}
					else {
						stk.pop();
						continue;
					}
				}
				else if (brackets[i] == '}') {
					if (stk.peek() != '{') {
						answer = 0;
						break;
					}
					else {
						stk.pop();
						continue;
					}
				}
				else if (brackets[i] == '>') {
					if (stk.peek() != '<') {
						answer = 0;
						break;
					}
					else {
						stk.pop();
						continue;
					}
				}
				else {
					stk.push(brackets[i]);
				}
			}
			
			if (!stk.isEmpty()) {
				answer = 0;
			}
			
			stk.clear();
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
