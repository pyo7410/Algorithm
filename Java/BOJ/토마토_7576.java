import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7576 {
	public static class Info {
		int y;
		int x;
		
		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}		
	}
	
	public static int N, M;
	
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	public static int[][] box;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");		
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0 ; j < M; ++j) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Info> q = new LinkedList<Info>();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (box[i][j] == 1) {
					q.offer(new Info(i, j));
				}
			}
		}
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
					continue;
				}
				
				if (box[ny][nx] == 0) {
					q.offer(new Info(ny, nx));
					box[ny][nx] = box[info.y][info.x] + 1;
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (box[i][j] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
				
				if (box[i][j] > answer) {
					answer = box[i][j] - 1;
				}
			}
		}
		
		System.out.println(answer);
	}
}
