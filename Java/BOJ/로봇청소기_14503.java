import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {
	public static int answer;
	public static int N, M;
	public static int r, c, d;
	public static int[][] map;
	
	// 북 동 남 서
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		go(r, c, d);
		
		System.out.println(answer);
	}
	
	public static void go(int y, int x, int dir) {
		// 1. 현재 위치를 청소한다.
		if (map[y][x] == 0) {
			map[y][x] = -1;
			answer++;
		}
		
		// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
		for (int i = 1; i <= 4; ++i) {
			dir -= 1;
			
			if (dir < 0) {
				dir = 3;
			}
			
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			// 2-b. 왼쪽 방향에 청소할 공간이 없다면 그 방향으로 회전한 다음 2번으로
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
				continue;
			}
			
			// 2-a. 왼쪽 방향에 아직 청소하지 않은 공간 존재
			if (map[ny][nx] == 0) {
				// 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행
				go(ny, nx, dir);
				return;
			}
			else if (map[ny][nx] != 0) { // 2-b. 왼쪽 방향에 청소할 공간이 없다면
				// 그 방향으로 회전한 다음 2번으로
				continue;
			}
		}
		
		// 2-c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로
		int ny = y - dy[dir];
		int nx = x - dx[dir];
		
		if (ny >= 0 && nx >= 0 && ny < N || nx < M) {
			if (map[ny][nx] != 1) {
				go(ny, nx, dir);
			}
		}
		
		// 2-d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
		return;
	}
}
