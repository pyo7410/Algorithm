import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길_1520 {
	public static int M, N;
	public static int[][] map;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		memo = new int[M][N];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j] = -1;
			}
		}
		
		go(0, 0);
		
		System.out.println(memo[0][0]);
	}
	
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] dx = {0, -1, 0, 1};
	public static int go(int y, int x) {
		if (y == M - 1 && x == N - 1) {
			// 도착했으므로 1 반환
			return 1;
		}
		
		// 이미 방문한적 있으므로 저장된 값을 반환
		if (memo[y][x] > -1) {
			return memo[y][x];
		}
		
		// 방문이 가능하므로 현재 위치를 방문했음을 알리기 위해 -1이었던 memo의 값을 0으로 변경
		memo[y][x] = 0;
		
		for (int dir = 0; dir < 4; ++dir) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (ny < 0 || nx < 0 || ny >= M || nx >= N) {
				continue;
			}
			
			// 현재보다 작은 수만 탐방
			if (map[ny][nx] < map[y][x]) {
				// 앞으로 갈 경로들의 도착지 도착횟수를 모두 구해 저장한다.
				memo[y][x] += go(ny, nx);
			}
		}
		
		return memo[y][x];
	}
}
