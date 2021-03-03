package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Info {
	public int x;
	public int y;
	
	public Info(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class 보급로_1249 {
	public static int N, answer;
	public static int[][] map, memo;
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			memo = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; ++i) {
				String input = br.readLine();
				for (int j = 0; j < N; ++j) {
					map[i][j] = input.charAt(j) - '0';
				}
			}
			
			answer = Integer.MAX_VALUE;
			bfs();
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(0, 0));
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			if (info.x == N - 1 && info.y == N - 1) {
				answer = (answer > memo[info.y][info.x]) ? memo[info.y][info.x] : answer;
				continue;
			}
			
			if (answer <= memo[info.y][info.x]) {
				continue;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				if (!visited[ny][nx] || memo[info.y][info.x] + map[ny][nx] < memo[ny][nx]) {
					visited[ny][nx] = true;
					memo[ny][nx] = memo[info.y][info.x] + map[ny][nx];
					q.offer(new Info(ny, nx));
				}
			}
		}
	}
} 
