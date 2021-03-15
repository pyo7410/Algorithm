package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 4613_러시아국기같은깃발 {
	public static int N, M, answer;
	public static char[][] flag;
	public static char[] colors;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			flag = new char[N][M];
			colors = new char[N];
			
			for (int i = 0; i < N; ++i) {
				flag[i] = br.readLine().toCharArray();
			}
			
			answer = 987654321;
			
			go(0, 'W');
			
			for (int i = 0; i < M; ++i) {
				if (flag[0][i] != 'W') {
					answer++;
				}
				if (flag[N - 1][i] != 'R') {
					answer++;
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void go(int cnt, char cur_color) {
		// 맨 마지막과 맨 처음은 항상 하얀색과 빨간색!
		if (cnt == N - 2) {
			int maxColorCnt = 0;
			for (int i = 1; i < N - 1; ++i) {
				int colorCnt = 0;
				
				for (int j = 0; j < M; ++j) {
					if (flag[i][j] != colors[i - 1]) {
						colorCnt++;
					}
				}
				
				maxColorCnt += colorCnt;
			}
			
			answer = (answer > maxColorCnt) ? maxColorCnt : answer;
			return;
		}
		
		if (cnt == N - 3 && cur_color == 'W') {
			colors[cnt] = 'B';
			go(cnt + 1, 'B');
			return;
		}
		
		if (cur_color == 'W') {
			colors[cnt] = 'W';
			go(cnt + 1, 'W');
			colors[cnt] = 'B';
			go(cnt + 1, 'B');
		}
		else if (cur_color == 'B') {
			colors[cnt] = 'B';
			go(cnt + 1, 'B');
			colors[cnt] = 'R';
			go(cnt + 1, 'R');
		}
		else {
			colors[cnt] = 'R';
			go(cnt + 1, 'R');
		}
	}
}
