import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳_1987 {
	public static int R, C, answer;
	public static boolean[] visited;
	public static char[][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 알파벳의 개수는 총 26개 이므로
		visited = new boolean[27];
		board = new char[R][C];
		
		for (int i = 0; i < R; ++i) {
			board[i] = br.readLine().toCharArray();
		}
		
		// 시작점은 1, 1이지만 0, 0부터 설정함
		go(0, 0, 1);
		
		System.out.println(answer);
	}
	
	public static int dy[] = {-1, 0, 1, 0};
	public static int dx[] = {0, -1, 0, 1};
	public static void go(int y, int x, int cnt) {
		// 방문처리
		visited[board[y][x] - 'A'] = true;
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 이미 방문한 알파벳이거나 범위를 벗어나면
			if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited[board[ny][nx] - 'A']) {
				continue;
			}
			
			// 조건에 맞는다면 다음 방문 검사
			go(ny, nx, cnt + 1);
		}
		
		// 다음 방문검사를 위해 방문처리 해제
		visited[board[y][x] - 'A'] = false;
		answer = (answer < cnt) ? cnt : answer;
	}
}
