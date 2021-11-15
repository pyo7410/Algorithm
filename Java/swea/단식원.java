import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 단식원 {
	public static int N, M, answer;
	public static boolean[][] visited;
	public static int[][] map, copyMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < M; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			go(0, 0, 3);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void go(int y, int x, int cnt) {
		if (y == N) {
			if (cnt > 0) {
				return;
			}
			
			visited = new boolean[N][M];
			copyMap = new int[N][M];
			
			copy(map, copyMap);
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (!visited[i][j] && copyMap[i][j] == 2) {
						bfs(i, j);
					}
				}
			}
			
			int result = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (copyMap[i][j] == 0) {
						result += 1;
					}
				}
			}
			
			answer = (answer < result) ? result : answer;
			
			return;
		}
		
		if (x == M) {
			go(y + 1, 0, cnt);
			return;
		}
		
		if (cnt > 0) {
			if (map[y][x] != 2) {
				int temp = map[y][x];
				
				map[y][x] = 1;
				go(y, x + 1, cnt - 1);
				map[y][x] = temp;
			}
		}
		
		go(y, x + 1, cnt);
	}
	
	public static void copy(int[][] from, int[][] to) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void bfs(int y, int x) {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(y, x));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				if (!visited[ny][nx] && copyMap[ny][nx] == 0) {
					copyMap[ny][nx] = 2;
					q.offer(new Info(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
	}
}
