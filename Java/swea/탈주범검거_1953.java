import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거_1953 {
	public static class Info {
		int y, x, time;

		public Info(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}
	
	public static int N, M, R, C, L, answer;
	public static boolean[][] visited;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][M];
			map = new int[N][M];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			bfs();
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 인덱스 -> 0 : 상, 1 : 좌, 2 : 하, 3 : 우
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] dx = {0, -1, 0, 1};
	
	// 인덱스 -> 0 : 상, 1 : 좌, 2 : 하, 3 : 우
	// 만약, 좌일 경우 우로 바꾸어야하므로 상과 하를 짝수, 좌와 우를 홀수 인덱스에 두어
	// (i + 2) % 4를 하여 바꾸어줄 수 있다.
	public static int[][] dir = {{0, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 0}, {1, 1, 0, 0}};
	
	public static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(R, C, 1));
		visited[R][C] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			// 이동했으므로 정답 +1
			answer++;
			
			// L이후는 조사할 필요가 없다.
			if (info.time >= L) {
				continue;
			}
			
			// 현재 파이프의 타입
			int type = map[info.y][info.x];
			
			for (int i = 0; i < 4; ++i) {
				if (dir[type][i] == 0) {
					continue;
				}
				
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				
				if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
					continue;
				}
				
				if (dir[map[ny][nx]][(i + 2) % 4] == 0) {
					continue;
				}
				
				if (!visited[ny][nx] && map[ny][nx] != 0) {
					q.offer(new Info(ny, nx, info.time + 1));
					visited[ny][nx] = true;
				}
			}
		}
	}
}
