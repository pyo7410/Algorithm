package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최장증가부분수열 {
	public static int N, answer;
	public static int[] arr;
	public static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N];
			memo = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N; ++i) {
				// 처음에는 자기자신만 있는게 최장길이수열이다!
				memo[i] = 1;
				
				for (int j = 0; j < i; ++j) {
					if (arr[i] >= arr[j] && memo[i] < memo[j] + 1) {
						memo[i] = memo[j] + 1;
					}
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
