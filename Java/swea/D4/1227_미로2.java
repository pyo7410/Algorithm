package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 미로2_1227 {
	public static char[][] maze = new char[100][100];
	public static boolean answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		for (int tc = 1; tc <= 10; ++tc) {
			br.readLine();
			
			answer = false;		
			for (int i = 0; i < 100; ++i) {
				maze[i] = br.readLine().toCharArray();
			}
			
			dfs(1, 1);
			
			sb.append("#").append(tc).append(" ").append(answer ? 1 : 0).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static void dfs(int y, int x) {
		maze[y][x] = '1';
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= 100 || nx >= 100) {
				continue;
			}
			
			if (maze[ny][nx] == '3') {
				answer = true;
				return;
			}
		
			if (maze[ny][nx] == '0') {
				dfs(ny, nx);
			}
		}
	}
}
