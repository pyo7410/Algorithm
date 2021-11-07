package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원점으로집합_8458 {
	public static int N, answer;
	public static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			memo = new int[N];
			
			// 거리를 일직선상의 좌표로 생각해서 풀면 된다.
			// 이때, N개의 점의 거리가 전부 홀수이거나 전부 짝수여야지만 0으로 이동이 가능하다. 
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int x = Math.abs(Integer.parseInt(st.nextToken()));
				int y = Math.abs(Integer.parseInt(st.nextToken()));
				
				int dist = x + y;
				
				if (dist % 2 == 0) {
					memo[i] = ((x < y) ? y : x) * 2;
				}
				else {
					memo[i] =  (((x < y) ? y : x) * 2) - 1;
				}
			}
			
			answer = 0;
			for (int i = 0; i < N; ++i) {
				answer = (answer < memo[i]) ? memo[i] : answer;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
