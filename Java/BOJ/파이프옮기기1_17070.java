import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기1_17070 {
	public static int N, answer;
	public static int[][] home;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		home = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		movePipe(0, 1, 0);
		
		System.out.println(answer);
	}
	
	// 가로, 세로, 대각선
	public static int[] dy = {0, 1, 1};
	public static int[] dx = {1, 0, 1};
	public static void movePipe(int r, int c, int dir) {
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}
		
		for (int i = 0; i < 3; ++i) {
			// 가로일때 세로로 못가고 세로일때 가로로 못감
			if ((i == 0 && dir == 1) || (i == 1 && dir == 0)) {
				continue;
			}
			
			int ny = r + dy[i];
			int nx = c + dx[i];
			
			// 범위검사 및 가야할 위치에 벽이 있는지
			if (ny >= N || nx >= N || home[ny][nx] == 1) {
				continue;
			}
			
			// 대각선일 경우 추가적인 위치에 벽이 있는지
			if (i == 2 && (home[ny - 1][nx] == 1 || home[ny][nx - 1] == 1)) {
				continue;
			}
			
			movePipe(ny, nx, i);
		}
	}
	
	public static void movePipe2(int r, int c, int dir) {
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}
		
		if (dir != 1) {
			if (c + 1 < N && home[r][c + 1] != 1) {
				movePipe(r, c + 1, 0);
			}
		}
		
		if (dir != 0) {
			if (r + 1 < N && home[r + 1][c] != 1) {
				movePipe(r + 1, c, 1);
			}
		}
		
		if (r + 1 < N && c + 1 < N) {
			if (home[r + 1][c + 1] == 0 && home[r + 1][c] == 0 && home[r][c + 1] == 0) {
				movePipe(r + 1, c + 1, 2);
			}
		}
	}
}
