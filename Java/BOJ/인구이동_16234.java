import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동_16234 {
	public static boolean flag;
	public static int N, L, R, answer;
	public static boolean[][] visited;
	public static int[][] map, unionMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		do {
			flag = false;
			int num = 0;
			
			visited = new boolean[N][N];
			unionMap = new int[N][N];
			
			// 인구 이동 시작
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (!visited[i][j]) {
						bfs(i, j , ++num);
					}
				}
			}
			
			// 연합이 생겨 인구이동이 일어났다면
			if (flag) {
				// 인구이동이 발생했으므로 정답을 +1
				answer++;
			}
		} while (flag); // 연합이 안 생길때까지 반복
		
		System.out.println(answer);
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
	
	// num : 연합의 번호
	public static void bfs(int y, int x, int num) {
		// 연합의 총 인구를 구하기 위함
		int totalUnionPopulaion = 0;
		// 연합의 총 수를 구하기 위함
		int totalUnionCnt = 0;
		
		Queue<Info> q = new LinkedList<Info>();
		
		totalUnionPopulaion += map[y][x];
		totalUnionCnt++;
		
		// 어떤 연합인지 num을 통해 구분
		unionMap[y][x] = num;
		
		q.offer(new Info(y, x));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				// 문제의 조건에 있는 두 나라의 인구 차를 구함
				int diff = Math.abs(map[info.y][info.x] - map[ny][nx]);
				
				// 인구 차가 조건에 부합하지 않다면 이동 불가
				if (diff < L || diff > R) {
					continue;
				}
				
				if (!visited[ny][nx]) {
					totalUnionPopulaion += map[ny][nx];
					totalUnionCnt++;
					
					// 어떤 연합인지 num을 통해 구분
					unionMap[ny][nx] = num;
					
					q.offer(new Info(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
		
		// 나라가 2개 이상이어야 연합이 생기므로
		if (totalUnionCnt > 1) {
			// 연합이 생겼으므로 flag를 true로
			flag = true;
			
			// 연합의 나라별 인구를 구함
			int unionPopulation = totalUnionPopulaion / totalUnionCnt;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (unionMap[i][j] == num) {
						// num에 해당하는 연합에 포함된 나라의 인구를 갱신한다.
						map[i][j] = unionPopulation;
					}
				}
			}
		}
	}
}
