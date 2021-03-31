import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형_1932 {
	public static int N;
	public static int[][] triangle;
	public static int[][] memo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		triangle = new int[N + 1][N + 1];
		memo = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 1; j <= i; ++j) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= i; ++j) {
				memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - 1]) + triangle[i][j];	
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			answer = (answer < memo[N][i]) ? memo[N][i] : answer;
		}
		
		System.out.println(answer);
	}
}
