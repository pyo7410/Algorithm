import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산_2573 {
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static int N, M;
	public static List<Info> list;
	public static boolean[][] visited;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new ArrayList<Info>();
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음입력부터 2개의 빙하일 수도 있으므로 미리 카운트를 한다.
		int cnt = getIcebergCnt();
		int year = 0;
		
		// 처음입력부터 2개의 빙하일 수도 있으므로 체크
		while (cnt < 2) {
			if (cnt == 0) {
				year = 0;
				break;
			}
			
			// 1년이 지나면
			year++;
			// 빙하가 녹고
			meltIceburg();
			// 녹은 빙하의 개수가 2개이상이 됬는지 카운트한다.
			cnt = getIcebergCnt();
			
		}
		
		System.out.println(year);
	}
	
	public static int dy[] = {-1, 0, 1, 0};
	public static int dx[] = {0, -1, 0, 1};
	public static void meltIceburg() {
		Queue<Info> q = new LinkedList<Info>();
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				// 0이 아닌 부분 즉, 빙하를 미리 넣는다.
				if (map[i][j] > 0) {
					q.offer(new Info(i, j));
					// 0이 되어버린 지점은 카운팅되면 안되므로 미리 방문처리를 해놔야한다.
					visited[i][j] = true;
				}
			}
		}
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			int cnt = 0;
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx]) {
					continue;
				}
				
				// 0이 되어버린 지점은 카운팅되면 안되므로 미리 방문처리를 해놔야한다.
				if (!visited[ny][nx] && map[ny][nx] == 0) {
					// 빙하주변에 있는 바다의 개수를 센다
					cnt++;
				}
			}
			
			// 빙하 주변의 바다 수만큼 빙하를 녹인다
			int sum = map[info.y][info.x] - cnt;
			// 만약 0보다 작아진다면 문제의 조건에 맞추어 0으로 고정해야한다.
			map[info.y][info.x] = (sum > 0) ? sum : 0;
		}
	}
	
	public static int getIcebergCnt() {
		int cnt = 0;
		visited = new boolean[N][M];		
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (!visited[i][j] && map[i][j] > 0) {
					// 1보다 크다면 빙하의 개수가 2개이상이므로 더이상 조사할 필요 X
					if (cnt > 1) {
						break;
					}
					
					// 인접한 빙하를 전부 조사한다.
					bfs(i, j);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	public static void bfs(int y, int x) {
		Queue<Info> q = new LinkedList<Info>();
		visited = new boolean[N][M];
		
		q.offer(new Info(y, x));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx]) {
					continue;
				}
				
				if (map[ny][nx] > 0) {
					q.offer(new Info(ny, nx));
					visited[ny][nx] = true;
					continue;
				}
			}
		}
	}
}
