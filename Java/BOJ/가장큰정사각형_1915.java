import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형_1915 {
	public static int N, M;
	public static int[][] arr;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][M + 1];
		memo = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; ++i) {
			String input = br.readLine();
			
			for (int j = 0; j < M; ++j) {
				arr[i][j + 1] = input.charAt(j) - '0';
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				if (arr[i][j] == 1) {
					memo[i][j] = Math.min(memo[i - 1][j], Math.min(memo[i - 1][j - 1], memo[i][j - 1])) + 1;
					
					// 정사각형이므로
					int area = memo[i][j] * memo[i][j];
					answer = (answer < area) ? area : answer;
				}
			}
		}
		
		System.out.println(answer);
	}
}
