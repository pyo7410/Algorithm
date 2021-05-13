import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 테트로미노_14500 {
	public static int N, M, answer;
	public static boolean[][] visited;
	public static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				visited[i][j] = true;
				go(i, j, 0, arr[i][j]);
				visited[i][j] = false;
				
				// 'ㅗ' 모양은 재귀로는 만들 수 없다.
				if (i + 1 < N && j + 1 < M && j - 1 >= 0) {
					int sum = arr[i][j] + arr[i + 1][j] + arr[i][j + 1] + arr[i][j - 1];
					answer = (answer < sum) ? sum : answer;
				}
				if (i + 1 < N && i - 1 >= 0 && j - 1 >= 0) {
					int sum = arr[i][j] + arr[i + 1][j] + arr[i - 1][j] + arr[i][j - 1];
					answer = (answer < sum) ? sum : answer;
				}
				if (i - 1 >= 0 && j + 1 < M && j - 1 >= 0) {
					int sum = arr[i][j] + arr[i - 1][j] + arr[i][j + 1] + arr[i][j - 1];
					answer = (answer < sum) ? sum : answer;
				}
				if (i + 1 < N && i - 1 >= 0 && j + 1 < M) {
					int sum = arr[i][j] + arr[i + 1][j] + arr[i - 1][j] + arr[i][j + 1];
					answer = (answer < sum) ? sum : answer;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void go(int y, int x, int cnt, int sum) {
		if (cnt >= 3) {
			answer = (answer < sum) ? sum : answer;
			return;
		}
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
				continue;
			}
			
			if (!visited[ny][nx]) {
				visited[ny][nx] = true;
				go(ny, nx, cnt + 1, sum + arr[ny][nx]);
				visited[ny][nx] = false;
			}
		}
	}
}
