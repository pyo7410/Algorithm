import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지_4485 {
	public static class Info implements Comparable<Info> {
		int y, x, loseMoney;

		public Info(int y, int x, int loseMoney) {
			this.y = y;
			this.x = x;
			this.loseMoney = loseMoney;
		}
		
		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.loseMoney, o.loseMoney);
		}
	}
	public static int N, answer;
	public static boolean[][] visited;
	public static int[][] cave;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			visited = new boolean[N][N];
			cave = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < N; ++j) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			go();
			sb.append("Problem ").append(tc++).append(": ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void go() {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, cave[0][0]));
		visited[0][0] = true;
		
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			
			if (info.y == N - 1 && info.x == N - 1) {
				answer = info.loseMoney;
				return;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				if (!visited[ny][nx]) {
					pq.offer(new Info(ny, nx, info.loseMoney + cave[ny][nx]));
					visited[ny][nx] = true;
				}
			}
		}
	} 
}
