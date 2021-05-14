import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 적록색약_10026 {
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static int N;
	public static boolean[][] visited;
	public static char[][] painting;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		
		painting = new char[N][N];
		
		for (int i = 0; i < N; ++i) {
			painting[i] = br.readLine().toCharArray();
		}
		
		int[] answer = new int[2];
		
		// 일반
		visited = new boolean[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!visited[i][j]) {
					bfs(i, j);
					answer[0]++;
				}
			}
		}
		
		// 적록색약 녹색을 빨강으로 바꾸어준다.
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (painting[i][j] == 'G') {
					painting[i][j] = 'R';
				}
			}
		}
		
		visited = new boolean[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!visited[i][j]) {
					bfs(i, j);
					answer[1]++;
				}
			}
		}
		
		System.out.println(answer[0] + " " + answer[1]);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void bfs(int y, int x) {
		Queue<Info> q = new LinkedList<Info>();
		visited[y][x] = true;
		q.offer(new Info(y, x));
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				if (!visited[ny][nx] && painting[ny][nx] == painting[y][x]) {
					visited[ny][nx] = true;
					q.offer(new Info(ny, nx));
				}
			}
		}
	}
}
