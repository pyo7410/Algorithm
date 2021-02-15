package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알덴테스파게티_8457 {
	public static int N, B, E;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int answer = 0;
			
			int max_time = B + E;
			int min_time = B - E;
			
			for (int i = 0; i < N; ++i) {
				int sandglass = Integer.parseInt(st.nextToken());
				int totalTime = sandglass;
				
				while (totalTime <= max_time) {
					if (totalTime >= min_time) {
						answer++;
						break;
					}
					
					totalTime += sandglass;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
