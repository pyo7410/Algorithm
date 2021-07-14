import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2_17472 {
	public static int N, M, islandCnt, from;
	public static boolean[] visited = new boolean[6];
	public static int[] minEdge = new int[6];
	public static int[][] adjMatrix = new int[6][6];
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		// 입력
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// visit 처리를 하지 않기위해 islandCnt를 2로 주고
		// 1인 지점을 2, 3, 4,... 순으로 바꾸어 visit 처리를 안하고 bfs를 한다.
		islandCnt = 2;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == 1) {
					bfs(i, j, islandCnt);
					islandCnt++;
				}
			}
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] > 0) {
					for (int k = 0; k < 4; ++k) {
						// 다리를 연결한다.
						int bridgeLen = go(i, j, k);
						
						// 다리의 수가 2보다 작으면 연결 X
						if (bridgeLen < 2) {
							continue;
						}
						
						// 다리를 연결하되 예를들어 1섬 -> 2섬으로의 다리 길이가 3이었다가 다른 쪽에서 다리를 연결해보니
						// 1섬 -> 2섬으로의 다리 길이가 2가 되면 이때 2를 선택해야 한다.
						if (adjMatrix[map[i][j] - 2][from - 2] == 0) {
							adjMatrix[map[i][j] - 2][from - 2] = bridgeLen;
						}
						else if (adjMatrix[map[i][j] - 2][from - 2] > bridgeLen) {
							adjMatrix[map[i][j] - 2][from - 2] = bridgeLen;	
						}
					}
				}
			}
		}
		
		// 프림 알고리즘
		int answer = 0;
		
		// visit 처리를 안하기위해 islandCnt에 +2를 했으므로 -2를 한다.
		int cnt = islandCnt - 2;
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;
		
		for (int c = 0; c < cnt; ++c) {
			int min = Integer.MAX_VALUE;
			int minIdx  = 0;
			
			for (int i = 0; i < cnt; ++i) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minIdx = i;
				}
			}
			
			answer += min;
			visited[minIdx] = true;
			
			for (int i = 0; i < cnt; ++i) {
				if (!visited[i] && adjMatrix[minIdx][i] != 0 && minEdge[i] > adjMatrix[minIdx][i]) {
					minEdge[i] = adjMatrix[minIdx][i];
				}
			}
		}
		
		// 프림 알고리즘 이후에 visited가 false인 섬이 있다면 해당 섬은 다리를 못 놓는 섬이되므로 
		// 모든 섬이 연결되지 않았으므로 -1 출력
		for (int i = 0; i < cnt; ++i) {
			if (!visited[i]) {
				answer = -1;
				break;
			}
		}
		
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
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	// 연결되있는 섬끼리 매칭시켜준다.
	public static void bfs(int y, int x, int n) {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(y, x));
		map[y][x] = n;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				if (map[ny][nx] == 1) {
					map[ny][nx] = n;
					q.offer(new Info(ny, nx));
				}
			}
		}
	}
	
	// 다리를 놓아 다리의 개수를 리턴한다.
	public static int go(int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		int cnt = 0;
		while (ny < N && nx < M && nx >= 0 && ny >= 0) {
			if (map[ny][nx] != 0) {
				from = map[ny][nx];
				return cnt;
			}
			
			cnt++;
			ny += dy[dir];
			nx += dx[dir];
		}
		
		return 0;
	}
}
