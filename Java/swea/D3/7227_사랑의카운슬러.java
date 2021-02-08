package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사랑의카운슬러_7227 {
	public static int N;
	public static long answer;
	public static int[] number;
	public static int[][] earthworms;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			answer = Long.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			earthworms = new int[N][2];
			number = new int[N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				earthworms[i][0] = Integer.parseInt(st.nextToken());
				earthworms[i][1] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void combination(int cnt, int start) {
		if (cnt == N / 2) {
			long dx = 0, dy = 0;
			for (int i = 0; i < N; ++i) {
				if (number[i] > 0) {
					dx += earthworms[i][0];
					dy += earthworms[i][1];
				}
				else {
					dx -= earthworms[i][0];
					dy -= earthworms[i][1];
				}
			}
			long vectorSize = (dx * dx) + (dy * dy);
			
			answer = (answer > vectorSize) ? vectorSize : answer;
			return;
		}
		
		for (int i = start; i < N; ++i) {
			number[i] = 1;
			combination(cnt + 1, i + 1);
			number[i] = 0;
		}
	}
}
