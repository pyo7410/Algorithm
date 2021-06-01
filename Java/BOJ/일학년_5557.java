import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일학년_5557 {
	//https://lmcoa15.tistory.com/96
	
	public static int N;
	public static int[] arr;
	
	// 결과가 2^63 - 1 이하라는 것을 주의
	public static long[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		memo = new long[N + 1][21];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 시작 숫자에 해당하는 인덱스 위치의 값을 +1
		memo[1][arr[1]]++;
		
		// 맨 마지막 숫자는 결과의미로 맨 마지막 숫자 이전까지 반복
		for (int i = 2; i < N; ++i) {
			for (int j = 0; j <= 20; ++j) {
				// 이전 계산결과의 숫자에 해당하는 인덱스가 0보다 크다면
				// 즉, 이전에 계산된 수라는 의미
				if (memo[i - 1][j] > 0) {
					// 만약 이전의 숫자 j와 현재 숫자인 arr[i]와 더했을때 20보다 작다면
					if (arr[i] + j <= 20) {
						// 현재 계산된 결과에 해당하는 인덱스의 값에 이전 숫자까지의 결과를 더한다.
						memo[i][arr[i] + j] += memo[i - 1][j];
					}
					// 뺄때 순서를 조심!
					// 이전의 숫자에서 빼야하므로 j에다가 현재 숫자를 빼야한다.
					// 만약 이전의 숫자 j와 현재 숫자인 arr[i]와 뺏을때 0보다 크다면
					if (j - arr[i] >= 0) {
						// 현재 계산된 결과에 해당하는 인덱스의 값에 이전 숫자까지의 결과를 더한다.
						memo[i][j - arr[i]] += memo[i - 1][j];
					}
				}
			}
		}
		
		// 마지막 숫자가 나온 횟수인 arr[N]으로 간다.
		System.out.println(memo[N - 1][arr[N]]);
	}
}
