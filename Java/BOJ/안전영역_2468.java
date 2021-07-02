import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안전영역_2468 {
	public static int N, maxHeight, answer, cnt;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static boolean[][] visit;
	public static int[][] area;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = (maxHeight < area[i][j]) ? area[i][j] : maxHeight;
			}
		}
		
		// 아무것도 안잠길 경우 땅은 1개만 있다.
		answer = 1;
		for (int k = 1; k < maxHeight; ++k) {
			cnt = 0;
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (!visit[i][j] && area[i][j] > k) {
						cnt++;
						go(i, j, k);
					}
				}
			}
			
			answer = (answer < cnt) ? cnt : answer;
		}
		
		System.out.println(answer);
	}
	
	
	public static void go(int y, int x, int height) {
		visit[y][x] = true;
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}
			
			if (!visit[ny][nx] && area[ny][nx] > height) {
				go(ny, nx, height);
			}
		}
	}
}
