import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {
	public static int T, n;
	public static int[][] stickers;
	public static int[][] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; ++tc) {
			n = Integer.parseInt(br.readLine());
			
			stickers = new int[2][n + 1];
			memo = new int[2][n + 1];
			
			for (int i = 0; i < 2; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 1; j <= n; ++j) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			memo[0][1] = stickers[0][1];
			memo[1][1] = stickers[1][1];
			for (int i = 2; i <= n; ++i) {
				memo[0][i] = Math.max(memo[1][i - 1], Math.max(memo[0][i - 2], memo[1][i - 2])) + stickers[0][i];
				memo[1][i] = Math.max(memo[0][i - 1], Math.max(memo[0][i - 2], memo[1][i - 2])) + stickers[1][i];
			}
			
			System.out.println((memo[0][n] < memo[1][n]) ? memo[1][n] : memo[0][n]);
		}
	}
}
