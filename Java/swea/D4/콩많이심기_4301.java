package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 콩많이심기_4301 {
	public static int N, M, answer;
	public static int[][] field;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			field = new int[M][N];
			
			answer = 0;
//			go(0, 0);
			
			for (int i = 0; i < M; ++i) {
				for (int j = 0; j < N; ++j) {
					answer++;
					field[i][j] = 1;
					
					for (int k = 0; k < 4; ++k) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						
						if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
							continue;
						}
						
						if (field[ny][nx] == 1) {
							answer--;
							field[i][j] = 0;
							break;
						}
					}
				}
			}
			 
			 
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dx = {0, 0, -2, 2};
	public static int[] dy = {-2, 2, 0 ,0};
	
	public static void go(int y, int x) {
		if (y == M) {
			return;
		}
		
		if (x == N) {
			go(y + 1, 0);
			return;
		}
		
		answer++;
		field[y][x] = 1;
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}
			
			if (field[ny][nx] == 1) {
				answer--;
				field[y][x] = 0;
				break;
			}
		}
		
		go(y, x + 1);
	}
}
