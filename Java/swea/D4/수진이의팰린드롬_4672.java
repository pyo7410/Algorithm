package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 수진이의팰린드롬_4672 {
	public static int answer, palindromeCnt;
	public static String input_s, str;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			input_s = br.readLine();
			
			// 한 글자씩 자른다
			String[] arr = input_s.split("");
			Arrays.sort(arr);
			str = String.join("", arr);
			
			answer = 0;
			for (int i = 0; i < str.length(); ++i) {
				powerset(Character.toString(str.charAt(i)), i + 1);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void powerset(String s, int idx) {
		isPalindrome(s);
		
		if (idx == str.length()) {
			return;
		}
		
		powerset(s + Character.toString(str.charAt(idx)), idx + 1);
	}
	
	public static void isPalindrome(String s) {
		int start = 0;
		int end = s.length() - 1;
		
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return;
			}
			
			start++;
			end--;
		}
		
		answer++;
	}
}
