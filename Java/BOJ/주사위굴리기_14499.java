import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기_14499 {
	public static int N, M, x, y, k;
	public static int[] dice = new int[7];
	public static int[] opposite = {-1, 6, 5, 4, 3, 2, 1};
	public static int[] dy = {-1, 0, 0, -1, 1};
	public static int[] dx = {-1, 1, -1, 0, 0};
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 문제에서 x, y는 좌표라는 점을 주의!!
		// 즉, (x, y)
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int top = 1;
		int east = 3;
		int north = 2;
		
		StringBuilder sb = new StringBuilder("");
		st = new StringTokenizer(br.readLine(), " ");
		while (st.countTokens() > 0) {
			int dir = Integer.parseInt(st.nextToken());
			
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
				continue;
			}
			
			// 만약 벽에 부딛힐경우는 출력하면안되므로 이동도하면 안됨을 주의!
			y = ny;
			x = nx;
			
			if (dir == 1) {
				int temp = top;
				top = opposite[east];
				east = temp;
			}
			else if (dir == 2) {
				int temp = top;
				top = east;
				east = opposite[temp];
			}
			else if (dir == 3) {
				int temp = top;
				top = opposite[north];
				north = temp;
			}
			else if (dir == 4) {
				int temp = top;
				top = north;
				north = opposite[temp];
			}
			
			if (map[y][x] == 0) {
				map[y][x] = dice[opposite[top]];
			}
			else {
				dice[opposite[top]] = map[y][x];
				map[y][x] = 0;
			}
			
			sb.append(dice[top]).append("\n");
		}
		
		System.out.println(sb);
	}
}
