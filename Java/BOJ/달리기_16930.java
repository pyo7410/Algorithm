import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달리기_16930 {
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static int N, M, K, answer;
	public static Info start, end;
	public static int[][] visited;
	public static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new int[N][M];
		
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			map[i] = st.nextToken().toCharArray();
			
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		start = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		end = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		bfs();
		answer = visited[end.y - 1][end.x - 1] ;
		
		System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(start.y - 1, start.x - 1));
		visited[start.y - 1][start.x - 1] = 0;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			if (info.y == end.y - 1 && info.x == end.x - 1) {
				return;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y;
				int nx = info.x;
				for (int j = 0; j < K; ++j) {
					ny += dy[i];
					nx += dx[i];
					
					if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
						break;
					}
					
					if (map[ny][nx] == '#' || visited[ny][nx] < visited[info.y][info.x] + 1) {
						break;
					}
					
					if (visited[ny][nx] == Integer.MAX_VALUE && map[ny][nx] == '.') {
						visited[ny][nx] = visited[info.y][info.x] + 1;
						q.offer(new Info(ny, nx));
					}
				}
			}
		}
	}
}
