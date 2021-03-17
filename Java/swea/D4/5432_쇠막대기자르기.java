package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 쇠막대기자르기_5432 {
	public static int answer;
	public static String s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			Stack<Integer> stk = new Stack<Integer>();
			
			s = br.readLine();
			answer = 0;
			
			int idx = 0;
			for (int i = 0; i < s.length(); ++i) {
				if (s.charAt(i) == '(') {
					stk.push(idx++);
				}
				else if (s.charAt(i) == ')') {
					if (idx - stk.peek() == 1) {
						stk.pop();
						answer += stk.size();
					}
					else {
						stk.pop();
						answer += 1;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}
