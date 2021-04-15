import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드구매하기_11052 {
	public static int N;
	public static int[] P = new int[1001];
	public static int[] memo = new int[1001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; ++i) {
			// 4장을 뽑는다면 1-3, 2-2, 3-1, 4-0의 방법이 존재한다!
			for (int j = 1; j <= i; ++j) {
				// 각 방법들 중 최대값을 찾는다
				memo[i] = Math.max(memo[i], memo[i - j] + P[j]);
			}
		}
		
		System.out.println(memo[N]);
	}
}
