import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 알고스팟_1261 {
	public static int N, M;
	public static boolean[][][] visited;
	public static char[][] maze;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		visited = new boolean[N][M][N * M];
		
		for (int i = 0; i < N; ++i) {
			maze[i] = br.readLine().toCharArray();
		}
		
		System.out.println(go());
	}
	
	public static class Info implements Comparable<Info> {
		int y, x, cnt;
		

		public Info(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		public int compareTo(Info o) {
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}
	
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] dx = {0, -1, 0, 1};
	
	public static int go() {
		int breakCnt = 0;
		
		PriorityQueue<Info> pq = new PriorityQueue<Info>();
		pq.offer(new Info(0, 0, 0));
		visited[0][0][0] = true;
		
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			
			if (info.y == N - 1 && info.x == M - 1) {
				breakCnt = info.cnt;
				break;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				if (visited[ny][nx][info.cnt]) {
					continue;
				}
				
				if (maze[ny][nx] == '0') {
					pq.offer(new Info(ny, nx, info.cnt));
					visited[ny][nx][info.cnt] = true;
				}
				else {
					pq.offer(new Info(ny, nx, info.cnt + 1));
					visited[ny][nx][info.cnt] = true;
				}
			}
		}
		
		return breakCnt;
	}
}
