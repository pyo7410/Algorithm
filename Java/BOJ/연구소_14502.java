import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_14502 {
	public static int N, M, answer;
	public static int[][] map, copyMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		go(0, 0, 0);
		
		System.out.println(answer);
	}
	
//	public static void go(int y, int x, int cnt) {
//		if (cnt == 3) {
//			// 바이러스 전파
//			bfs();			
//			
//			// 안전영역 찾기
//			int saftCnt = 0;
//			for (int i = 0; i < N; ++i) {
//				for (int j = 0; j < M; ++j) {
//					if (copyMap[i][j] == 0) {
//						saftCnt++;
//					}
//				}
//			}
//			
//			// 정답 갱신
//			answer = (answer < saftCnt) ? saftCnt : answer;
//			
//			return;
//		}
//		
//		if (y == N) {
//			return;
//		}
//		
//		if (x == M) {
//			go(y + 1, 0, cnt);
//			return;
//		}
//		
//		if (map[y][x] == 0) {
//			// 1로 바꾸는 경우
//			map[y][x] = 1;
//			go(y, x + 1, cnt + 1);
//			// 1로 안바꾸는 경우
//			map[y][x] = 0;
//		}
//		
//		go(y, x + 1, cnt);
//	}
//	
//	public static class Info {
//		int y, x;
//
//		public Info(int y, int x) {
//			super();
//			this.y = y;
//			this.x = x;
//		}
//	}
//	
//	public static int[] dx = {0, 0, -1, 1};
//	public static int[] dy = {-1, 1, 0, 0};
//	
//	public static void bfs() {
//		Queue<Info> q = new LinkedList<Info>();
//		
//		for (int i = 0; i < N; ++i) {
//			for (int j = 0; j < M; ++j) {
//				// 배열의 값이 계속 바뀌어도 다시 원본배열이 필요해지므로 배열을 복사
//				copyMap[i][j] = map[i][j];
//				
//				// 바이러스의 시작 위치를 찾아 bfs의 시작으로 한다.
//				if (copyMap[i][j] == 2) {
//					q.offer(new Info(i, j));
//				}
//			}
//		}
//		
//		while (!q.isEmpty()) {
//			Info info = q.poll();
//			
//			for (int i = 0; i < 4; ++i) {
//				int ny = info.y + dy[i];
//				int nx = info.x + dx[i];
//				
//				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
//					continue;
//				}
//				
//				if (copyMap[ny][nx] == 0) {
//					copyMap[ny][nx] = 2;
//					q.offer(new Info(ny, nx));
//				}
//			}
//		}
//	}
	
	public static void go(int y, int x, int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					// 배열의 값이 계속 바뀌어도 다시 원본배열이 필요해지므로 배열을 복사
					copyMap[i][j] = map[i][j];
				}
			}
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (copyMap[i][j] == 2) {
						dfs(i, j);
					}
				}
			}
			
			// 안전영역 찾기
			int saftCnt = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (copyMap[i][j] == 0) {
						saftCnt++;
					}
				}
			}
			
			// 정답 갱신
			answer = (answer < saftCnt) ? saftCnt : answer;
			
			return;
		}
		
		if (y == N) {
			return;
		}
		
		if (x == M) {
			go(y + 1, 0, cnt);
			return;
		}
		
		if (map[y][x] == 0) {
			// 1로 바꾸는 경우
			map[y][x] = 1;
			go(y, x + 1, cnt + 1);
			// 1로 안바꾸는 경우
			map[y][x] = 0;
		}
		
		go(y, x + 1, cnt);
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	public static void dfs(int y, int x) {
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
				continue;
			}
			
			if (copyMap[ny][nx] == 0) {
				copyMap[ny][nx] = 2;
				dfs(ny, nx);
			}
		}
	}
}
