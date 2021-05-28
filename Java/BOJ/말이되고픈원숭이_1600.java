import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이_1600 {
	public static int K, W, H;
	public static boolean[][][] visited;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 마지막 차원은 부쉈는지 안부쉈는지 여부
		visited = new boolean[H][W][K + 1];
		map = new int[H][W];
		
		for (int i = 0; i < H; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < W; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		System.out.println(go());
	}
	
	public static class Info {
		// isUsed == 0 -> 벽을 부술 수 있다.
		// isUsed == 1 -> 벽을 부술 수 없다.
		int usedCnt, y, x, cnt;

		public Info(int usedCnt, int y, int x, int cnt) {
			super();
			this.usedCnt = usedCnt;
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	public static int[] hDx = {1, 2, 2, 1, -1, -2, -2, -1};
	public static int[] hDy = {2, 1, -1, -2, -2, -1, 1, 2};
	
 	public static int go() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(K, 0, 0, 0));
		visited[0][0][K] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			if (info.y == H - 1 && info.x == W - 1) {
				return info.cnt;
			}
			
			if (info.usedCnt > 0) {
				for (int i = 0; i < 8; ++i) {
					int ny = info.y + hDy[i];
					int nx = info.x + hDx[i];
					
					if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
						continue;
					}
					
					// 말처럼 이동하고나서 비교
					// 말처럼 이동했으니 능력 -1
					if (visited[ny][nx][info.usedCnt - 1]) {
						continue;
					}
					
					// 말처럼 이동하고나서 비교
					// 말처럼 이동했으니 능력 -1
					if (map[ny][nx] == 0) {
						visited[ny][nx][info.usedCnt - 1] = true;
						q.offer(new Info(info.usedCnt - 1, ny, nx, info.cnt + 1));
					}
				}				
			}

			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
					continue;
				}
				
				if (visited[ny][nx][info.usedCnt]) {
					continue;
				}
				
				// 갈 수 있는 곳이라면 전진
				if (map[ny][nx] == 0) {
					visited[ny][nx][info.usedCnt] = true;
					q.offer(new Info(info.usedCnt, ny, nx, info.cnt + 1));
				}
			}
		}
		
		return -1;
	}
}
