import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_16236 {
	public static class Info implements Comparable<Info> {
		int y, x, dist;

		public Info(int y, int x, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(Info o) {
			if (this.dist == o.dist) {
				if (this.y == o.y) {
					// 거리도 같고 위에 있는 거리도 같다면 가장 왼쪽의 물고기를 먹는다.
					return Integer.compare(this.x, o.x);
				}
				
				// 거리가 같으면 위에 있는 물고기를 우선
				return Integer.compare(this.y, o.y);
			}
			
			// 거리가 가까운 물고기를 우선
			return Integer.compare(this.dist, o.dist);
		}
	} 
	
	public static int N, answer, eatCnt;
	
	public static PriorityQueue<Info> pq = null;
	
	// 인덱스 -> 0 : i, 1 : j, 2 : 크기
	public static int[] babyShark = new int[3];
	public static boolean[][] visited;
	public static int[][] area;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		area = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < N; ++j) {
				area[i][j] = Integer.parseInt(st.nextToken());
				
				// 아기상어의 출발 지점 기록
				if (area[i][j] > 0) {
					if (area[i][j] == 9) {
						babyShark[0] = i;
						babyShark[1] = j;
						babyShark[2] = 2;
						area[i][j] = 0;
					}
				}
			}
		}
		
		while (true) {
			bfs();
			
			// 만약 먹을 수 있는 물고기가 없다면 엄마 상어에게 도움 요청
			if (pq.isEmpty()) {
				break;
			}
			
			// 조건에 맞고 우선순위가 제일 높은 물고기를 빼냄
			Info info = pq.poll();
			
			// 빼낸 물고기의 거리를 더해주어 거리 갱신
			answer += info.dist;
			
			// 물고기를 먹었으므로 +1
			eatCnt++;
			
			// 만약 먹을 물고기의 수가 현재 아기상어의 크기와 같다면
			if (eatCnt == babyShark[2]) {
				// 아기상어 크기 +1
				babyShark[2]++;
				
				// 먹은 물고기 수 초기화
				eatCnt = 0;
			}
			
			// 현재 자리의 물고기를 먹었으므로 0으로 초기화
			area[info.y][info.x] = 0;
			
			// 먹은 물고기의 위치로 아기상어 이동
			babyShark[0] = info.y;
			babyShark[1] = info.x;
		}
		
		System.out.println(answer);
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		visited = new boolean[N][N];
		
		Queue<Info> q = new LinkedList<Info>();
		pq = new PriorityQueue<Info>();
		
		q.offer(new Info(babyShark[0], babyShark[1], 0));
		visited[babyShark[0]][babyShark[1]] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				// 방문했거나 아기상어의 현재 크기보다 큰 물고기는 갈 수 없다
				if (visited[ny][nx] || area[ny][nx] > babyShark[2]) {
					continue;
				}
				
				// 먹을 수 있는 물고기이므로 pq에 추가
				if (area[ny][nx] > 0 && area[ny][nx] < babyShark[2]) {
					pq.offer(new Info(ny, nx, info.dist + 1));
				}
				
				// 이동이 불가능한 경우는 위에서 전부 처리했으므로 먹을 수 있는 다른 물고기를 찾아 계속 이동
				q.offer(new Info(ny, nx, info.dist + 1));
				visited[ny][nx] = true;
			}
		}
	}
}
