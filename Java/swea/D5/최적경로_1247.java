package D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최적경로_1247 {
	public static int N, answer;
	public static int[][] visitRoute;
	public static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			answer = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			visitRoute = new int[N + 2][2];
			check = new boolean[N + 2];
			
			// 입력의 처음은 회사 좌표, 두번째는 집 좌표임을 주의
			for (int i = 0; i < N + 2; ++i) {
				visitRoute[i][0] = Integer.parseInt(st.nextToken());
				visitRoute[i][1] = Integer.parseInt(st.nextToken());
			}
			
			permutation(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void permutation(int cnt, int start, int distance) {
		if (distance >= answer) {
			return;
		}
		
		// 회사와 집을 제외하면 N개를 뽑아야 하고 cnt를 0부터 시작했으므로
		if (cnt == N) {
			// visitRoute의 처음은 회사 좌표, 두번째는 집 좌표임을 주의
			distance += Math.abs(visitRoute[start][0] - visitRoute[1][0]);
			distance += Math.abs(visitRoute[start][1] - visitRoute[1][1]);
				
			if (answer > distance) {
				answer = distance;
			}
			
			return;
		}
		
		// visitRoute의 처음은 회사 좌표, 두번째는 집 좌표임을 주의
		for (int i = 2; i < N + 2; ++i) {
			if (check[i]) {
				continue;
			}
			
			int dist = Math.abs(visitRoute[start][0] - visitRoute[i][0]);
			dist += Math.abs(visitRoute[start][1] - visitRoute[i][1]);
			
			check[i] = true;
			permutation(cnt + 1, i, distance + dist);
			check[i] = false;
		}
	}
}
