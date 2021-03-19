package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수지의수지맞는여행_7699 {
	public static int R, C, answer;
	public static char[][] map;
	public static boolean[] alphabet;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			alphabet = new boolean[26];
			
			for (int i = 0; i < R; ++i) {
				map[i] = br.readLine().toCharArray();
			}
			
			answer = 0;
			alphabet[map[0][0] - 'A'] = true;
			go(0, 0, 1);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static void go(int y, int x, int cnt) {
		if (cnt > answer) {
			answer = cnt;
		}
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= C || ny >= R) {
				continue;
			}
			
			int idx = map[ny][nx] - 'A'; 
			
			if (!alphabet[idx]) {
				alphabet[idx] = true;
				go(ny, nx, cnt + 1);
				alphabet[idx] = false;
			}
		}
	}
}
