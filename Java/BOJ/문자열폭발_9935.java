import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발_9935 {
	public static String input, explosive;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine();
		explosive = br.readLine();
		
		Stack<Character> stk = new Stack<>();
		
		for (int i = 0; i < input.length(); ++i) {
			stk.push(input.charAt(i));
			
			if (stk.size() >= explosive.length()) {
				boolean flag = true;
				for (int j = 0; j < explosive.length(); ++j) {
					if (stk.get(stk.size() - 1 - j) != explosive.charAt(explosive.length() - 1 - j)) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					for (int j = 0; j < explosive.length(); ++j) {
						stk.pop();
					}
				}
			}
		}
		
		StringBuilder answer = new StringBuilder("");
		for (Character ch : stk) {
			answer.append(ch);
		}
		
		System.out.println((answer.length() == 0) ? "FRULA" : answer);
	}
}
