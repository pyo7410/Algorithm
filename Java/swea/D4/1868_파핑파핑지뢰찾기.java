package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파핑파핑지뢰찾기_1868 {
	public static int N, answer;
	public static char[][] table;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			table = new char[N][N];
			
			for (int i = 0; i < N; ++i) {
				table[i] = br.readLine().toCharArray();
			}
			
			answer = 0;
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (table[i][j] == '.' && isOpen(i, j)) {
						answer++;
						table[i][j] = 'x';
						go(i, j);
					}
				}
			}
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (table[i][j] == '.') {
						answer++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static void go(int y, int x) {
		if (isOpen(y, x)) {
			for (int i = 0; i < 8; ++i) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				if (table[ny][nx] == '.') {
					table[ny][nx] = 'x';
					go(ny, nx);
				}
			}
		}
	}
	
	public static boolean isOpen(int y, int x) {
		for (int i = 0; i < 8; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}
			
			if (table[ny][nx] == '*') {
				return false;
			}
		}
		
		return true;
	}
}
