import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_5427 {
	public static class Info {
		int y, x, cnt;

		// 상근이 이동 시 사용
		public Info(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
		// 불 이동 시 사용
		public Info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	public static int w, h, answer;
	public static Queue<Info> fireQ, q;
	public static boolean[][] visited;
	public static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < tc; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			visited = new boolean[h][w];
			map = new char[h][w];
			
			q = new LinkedList<>();
			fireQ = new LinkedList<>();
			
			for (int i = 0; i < h; ++i) {
				map[i] = br.readLine().toCharArray();
				
				for (int j = 0; j < w; ++j) {
					if (map[i][j] == '@') {
						// 상근이 위치를 .으로 변경 후 큐에 삽입
						map[i][j] = '.';
						q.offer(new Info(i, j, 0));
						visited[i][j] = true;
					}
					if (map[i][j] == '*') {
						// 불의 위치를 큐에 삽입
						fireQ.offer(new Info(i, j));
					}
				}
			} 
			
			answer = Integer.MAX_VALUE;
			bfs();
			
			sb.append((answer != Integer.MAX_VALUE) ? answer : "IMPOSSIBLE").append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static void bfs() {
		// 상근이 큐가 빌때까지 즉, 상근이가 이동할 수 없을때 까지 반복
		while (!q.isEmpty()) {
			// 먼저 불을 이동시켜 불이 붙으려는 칸으로 이동할 수 없도록 처리한다.
			// 이때, 현재 시간의 불들을 전부 이동시켜야 하므로 현재 큐에 들어있는 같은 시간대의 불들을
			// 전부 이동시키기 위해 큐의 size만큼 bfs를 수행한다.
			// 또한, 큐에는 계속해서 값이 들어가거나 빠지기 때문에 size를 미리 저장해야한다.
			int fireLen = fireQ.size();
			for (int i = 0; i < fireLen; ++i) {
				Info info = fireQ.poll();
				
				for (int dir = 0; dir < 4; ++dir) {
					int ny = info.y + dy[dir];
					int nx = info.x + dx[dir];
					
					if (ny < 0 || nx < 0 || ny >= h || nx >= w) {
						continue;
					}
					
					// 벽과 원래 불이었던 위치를 제외하고 불을 옮긴다.
					if (map[ny][nx] == '.') {
						fireQ.offer(new Info(ny, nx));
						map[ny][nx] = '*';
					}
				}
			}
			
			// 불 이동이 끝났다면 상근이를 이동시킨다.
			// 이때, 해당 시간에 있는 모든 이동경로를 이동시켜야 다음 시간의 불이 이동이 가능하므로
			// 현재 큐에 들어있는 같은 시간대의 이동경로를 전부 이동시키기 위해 큐의 사이즈만큼 bfs를 수행한다.
			// 이때, 큐에는 계속해서 값이 들어가거나 빠지기 때문에 size를 미리 저장해야한다.
			int qLen = q.size();
			for (int i = 0; i < qLen; ++i) {
				Info info = q.poll();
				
				// 만약 상근이가 맵의 경계에 있다면 이는 다음에 밖으로 탈출이 가능하다는 의미이므로
				// BFS의 특성상 최소가 되기에 현재까지의 cnt가 최소가 된다.
				if (info.y == 0 || info.x == 0 || info.y == h - 1 || info.x == w - 1) {
					answer = info.cnt + 1;
					return;
				}
				
				for (int dir = 0; dir < 4; ++dir) {
					int ny = info.y + dy[dir];
					int nx = info.x + dx[dir];
					
					if (ny < 0 || nx < 0 || ny >= h || nx >= w) {
						continue;
					}
					
					// 상근이가 방문하지 않았고 갈 수 있는 길이라면 전진
					if (!visited[ny][nx] && map[ny][nx] == '.') {
						q.offer(new Info(ny, nx, info.cnt + 1));
						visited[ny][nx] = true;
					}
				}
			}
		}
	}
}
