package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장빠른문자열타이핑_3143 {
	public static String A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			A = st.nextToken();
			B = st.nextToken();
			
			String answer = A.replaceAll(B, "c");
			
			sb.append("#").append(tc).append(" ").append(answer.length()).append("\n");
		}
		
		System.out.println(sb);
	}
}
