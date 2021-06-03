import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기3_16933 {
	public static int N, M, K;
	public static boolean[][][][] visited;
	public static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 마지막 - 1 차원은 부쉈는지 안부쉈는지 여부
		// 마지막은 낮밤여부
		visited = new boolean[N][M][K + 1][2];
		map = new char[N][M];
		
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(go());
	}
	
	public static class Info {
		// isUsed > 0 -> 벽을 부술 수 있다.
		// isUsed == 1 -> 벽을 부술 수 없다.
		
		// day == 0 -> 낮
		// day == 1 -> 밤
		int isUsed, y, x, cnt, day;

		public Info(int isUsed, int y, int x, int cnt, int day) {
			super();
			this.isUsed = isUsed;
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.day = day;
		}
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
 	public static int go() {
 		int minLen = Integer.MAX_VALUE;
 		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(K, 0, 0, 1, 0));
		visited[0][0][K][0] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			if (info.y == N - 1 && info.x == M - 1) {
				minLen = (minLen > info.cnt) ? info.cnt : minLen;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				if (ny == N - 1 && nx == M - 1) {
					minLen = (minLen > info.cnt + 1) ? info.cnt + 1 : minLen;
					return minLen;
				}
				
				if (visited[ny][nx][info.isUsed][info.day]) {
					continue;
				}
				
				// 갈 수 있는 곳이라면 전진
				if (map[ny][nx] == '0') {
					visited[ny][nx][info.isUsed][info.day] = true;
					q.offer(new Info(info.isUsed, ny, nx, info.cnt + 1, (info.day + 1) % 2));
					continue;
				}
				
				// 만약 갈 수 없는 곳인데 벽을 부술수있고
				if (map[ny][nx] == '1' && info.isUsed > 0) {
					// 방문하지 않은 곳이라면 부수고 방문
					visited[ny][nx][info.isUsed][info.day] = true;
					
					if (info.day == 0) {
						q.offer(new Info(info.isUsed - 1, ny, nx, info.cnt + 1, 1));
						continue;
					}
					else {
						q.offer(new Info(info.isUsed, info.y, info.x, info.cnt + 1, 0));
						continue;
					}
				}
			}
		}
		
		return (minLen == Integer.MAX_VALUE) ? -1 : minLen;
	}
}
