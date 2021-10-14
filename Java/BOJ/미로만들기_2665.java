import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 미로만들기_2665 {
	public static class Info implements Comparable<Info> {
		int y, x, changeCnt;

		public Info(int y, int x, int changeCnt) {
			this.y = y;
			this.x = x;
			this.changeCnt = changeCnt;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.changeCnt, o.changeCnt);
		}
	}
	
	public final static int MAX = 987654321;
	public static int N, answer;
	public static boolean[][] visited;
	public static int[][] maze;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N][N];
		maze = new int[N][N];		
		
		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			
			for (int j = 0; j < N; ++j) {
				maze[i][j] = input.charAt(j) - '0';
			}
		}
		
		System.out.println(go());
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static int go() {
		// 우선순위 큐를 사용하여 가장 적은 변경횟수를 갖고있는 위치를 우선적으로 이동
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, 0));
		visited[0][0] = true;
		
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			
			// 우선순위 큐를 사용하여 가장 적은 변경횟수를 갖고있는 위치를 우선적으로 이동하므로
			// 처음으로 도착지에 도착하는 정보는 가장 적은 수의 변경횟수를 갖게된다!
			if (info.y == N - 1 && info.x == N - 1) {
				return info.changeCnt;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) {
					continue;
				}
				
				if (maze[ny][nx] == 1) {
					pq.offer(new Info(ny, nx, info.changeCnt));
				}
				else if (maze[ny][nx] == 0) {
					pq.offer(new Info(ny, nx, info.changeCnt + 1));
				}
				
				visited[ny][nx] = true;
			}
		}
		
		// 문제에 조건에따라 아무것도 부수지않는다면 0을 출력한다.
		return 0;
	}
}
