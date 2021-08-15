import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이동하기_11048 {
	public static int N, M;
	public static int[][] maze;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N + 1][M + 1];
		memo = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; ++j) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				memo[i][j] = Math.max(memo[i - 1][j], Math.max(memo[i][j - 1], memo[i - 1][j - 1])) + maze[i][j];
			}
		}
		
		System.out.println(memo[N][M]);
	}
}
