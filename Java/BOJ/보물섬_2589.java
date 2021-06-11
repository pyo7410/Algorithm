import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보물섬_2589 {
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static int N, M, answer;
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static boolean[][] visited;
	public static char[][] map;
	public static int[][] distMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0 ; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				visited = new boolean[N][M];
				distMap = new int[N][M];
				
				if (map[i][j] == 'L' & !visited[i][j]) {
					bfs(i, j);
					calc();
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static void bfs(int y, int x) {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(y, x));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >=M) {
					continue;
				}
				
				if (!visited[ny][nx] && map[ny][nx] == 'L') {
					distMap[ny][nx] = distMap[info.y][info.x] + 1;
					q.offer(new Info(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
	}
	
	public static void calc() {
		for (int i = 0 ; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				answer = (answer < distMap[i][j]) ? distMap[i][j] : answer;
			}
		}
	}
}
