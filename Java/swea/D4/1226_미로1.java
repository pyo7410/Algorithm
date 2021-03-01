package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 미로1_1226 {
	public static int answer;
	public static char[][] maze = new char[16][16];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		for (int tc = 1; tc <= 10; ++tc) {
			br.readLine();
			
			for (int i = 0; i < 16; ++i) {
				maze[i] = br.readLine().toCharArray();
			}
			
			answer = 0;
			go(1, 1);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void go(int y, int x) {
		for (int i = 0; i < 4; ++i) {
			if (answer == 1) {
				return;
			}
			
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= 16 || nx >= 16) {
				continue;
			}
			
			if (maze[ny][nx] == '3') {
				answer = 1;
				return;
			}
			
			if (maze[ny][nx] == '0') {
				maze[ny][nx] = '1';
				go(ny, nx);
			}
		}
	}
}
