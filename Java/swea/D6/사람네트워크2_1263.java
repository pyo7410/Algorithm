package D6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사람네트워크2_1263 {
	public final static int INF = 987654321;
	public static int N;
	public static int[][] adjMatrix;
	public static int[][] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			adjMatrix = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
					
					if (i != j && adjMatrix[i][j] == 0) {
						adjMatrix[i][j] = INF;
					}
				}
			}
			
			// 경유지
			for (int k = 0; k < N; ++k) {
				// 출발지
				for (int i = 0; i < N; ++i) {
					// 만약 경유지와 출발지가 같다면 할필요가 없다.
					if (i == k) {
						continue;
					}
					// 도착지
					for (int j = 0; j < N; ++j) {
						// 만약 출발지와 도착지가 같거나 경유지와 도착지가 같다면 할 필요가 없다.
						if (i == j || k == j) {
							continue;
						}
						
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
					}
				}
			}
			
			int answer = INF;
			for (int i = 0; i < N; ++i) {
				int result = 0;
				for (int j = 0; j < N; ++j) {
					if (adjMatrix[i][j] != INF) {
						result += adjMatrix[i][j];
					}
				}
				
				answer = (answer > result) ? result : answer;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
