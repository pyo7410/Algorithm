package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한빈이와SpotMart_9229 {
	public static int N, M, answer;
	public static int[] weights;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			answer = -1;
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			weights = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void combination(int cnt, int start, int sum) {
		if (cnt == 2) {
			if (sum <= M) {
				answer = (answer > sum) ? answer : sum;
			}
				
			return;
		}
		
		for (int i = start; i < N; ++i) {
			combination(cnt + 1, i + 1, sum + weights[i]);
		}
	}
}
