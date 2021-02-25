package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 보물왕태혁_7829 {
	public static int N, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int num;
			
			if (st.countTokens() == 1) {
				num = Integer.parseInt(st.nextToken());
				answer = num * num;
			}
			else {
				for (int i = 0; i < N; ++i) {
					num = Integer.parseInt(st.nextToken());
					max = Math.max(max, num);
					min = Math.min(min, num);
				}
				
				answer = max * min;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
