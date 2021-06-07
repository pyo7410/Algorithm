import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출_3055 {
	public static class Info{
		int y, x, minute;

		public Info(int y, int x, int minute) {
			super();
			this.y = y;
			this.x = x;
			this.minute = minute;
		}
	}
	public static int R, C, answer;
	public static Queue<Info> hedgehog_q = new LinkedList<Info>();
	public static Queue<Info> water_q = new LinkedList<Info>();
	public static boolean[][] visited;
	public static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] == 'S') {
					hedgehog_q.offer(new Info(i, j, 0));
					visited[i][j] = true;
				}
				else if (map[i][j] == '*') {
					water_q.offer(new Info(i, j, 0));
				}
			}
		}
		
		answer = -1;
		bfs();
		
		System.out.println((answer == -1) ? "KAKTUS" : answer);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void bfs() {
		while (!hedgehog_q.isEmpty()) {
			Info info = hedgehog_q.poll();
			
			moveWater(info.minute);
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= R || nx >= C) {
					continue;
				}
				
				if (map[ny][nx] == 'D') {
					answer = info.minute + 1;
					return;
				}
				
				if (!visited[ny][nx] && map[ny][nx] == '.') {
					hedgehog_q.offer(new Info(ny, nx, info.minute + 1));
					visited[ny][nx] = true;
				}
			}
		}
	}
	
	public static void moveWater(int minute) {
		while (!water_q.isEmpty() && water_q.peek().minute == minute) {
			Info waterInfo = water_q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = waterInfo.y + dy[i];
				int nx = waterInfo.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= R || nx >= C) {
					continue;
				}
				
				if (map[ny][nx] == '.') {
					map[ny][nx] = '*';
					water_q.offer(new Info(ny, nx, waterInfo.minute + 1));
				}
			}
		}
	}
}
