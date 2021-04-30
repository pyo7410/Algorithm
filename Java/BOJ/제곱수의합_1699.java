import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 제곱수의합_16993 {
	public static int N;
	public static int[] memo = new int[100001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			// 최대의 항의 개수는 1^2 으로만 이루어진 경우이므로
			// 현재 숫자가 된다.
			memo[i] = i;
			
			// j^2이 i보다 작다면 i - (j ^ 2)를 한 수에서 j ^ 2 즉, 제곱수를 하나만 더 추가하면 된다.
			// 때문에 i - (j * j) 번째 까지의 최대 항의 개수 + 1을 한 값이 현재 저장된 최대항의 개수보다 작다면
			// 갱신되게 해야한다.
			for (int j = 1; j * j <= i; ++j) {
				if (memo[i] > memo[i - (j * j)] + 1) {
					memo[i] = memo[i - (j * j)] + 1;
				}
			}
		}
		
		System.out.println(memo[N]);
	}
}
