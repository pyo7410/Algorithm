package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사칙연산유효성검사_1233 {
	public static int N;
	public static String[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			tree = new String[N + 1];
			int answer = 1;
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				tree[Integer.parseInt(st.nextToken())] = st.nextToken();
			}
			
			int nodeIdx = N / 2;
			for (int i = 1; i < N + 1; ++i) {
				if (tree[i].charAt(0) >= '0' && tree[i].charAt(0) <= '9') {
					if (i <= nodeIdx) {
						answer = 0;
						break;
					}
				}
				else {
					if (i > nodeIdx) {
						answer = 0;
						break;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
