import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전_9084 {
	public static int T, N, money;
	public static int[] coins;
	public static int[] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			
			// 동전들을 저장한다.
			coins = new int[N];
			// 문제에서 최대로 구할 수 있는 돈은 10000원 까지임
			memo = new int[10001];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			
			// 문제에서 구하고자하는 금액
			money = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; ++i) {
				// 조사하고자하는 동전의 금액보다 작은 액수는 만들 수 없으므로
				// 현재 동전의 금액을 시작으로 구하고자하는 금액을 만드는 경우를 구한다.
				// 동전의 금액 한개도 동전의 금액을 만들 수 있는 경우이므로 1을 더한다.
				memo[coins[i]] += 1;
				for (int j = coins[i]; j <= money; ++j) {
					// 현재 금액 j 에서 현재 동전의 금액 coins[i]를 뺀 금액을 만드는 경우의 수를
					// 현재 금액 j의 경우에 수에 더해준다.
					memo[j] += memo[j - coins[i]];
				}
			}
			
			System.out.println(memo[money]);
		}
	}
}
