import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 소수의연속합_1644 {
	public static int N, answer;
	public static boolean[] isPrime;
	public static List<Integer> primeArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		isPrime = new boolean[N + 1];
		primeArr = new ArrayList<>();
		
		getPrime();
		solve();
		System.out.println(answer);
	}
	
	public static void solve() {
		int left = 0;
		int right = 0;
		int sum = 0;
		int primeCnt = primeArr.size();
		
		while (left <= right && right <= primeCnt) {
			if (sum >= N) {
				if (sum == N) {
					answer++;
				}
				sum -= primeArr.get(left++);
				continue;
			}
			else {
				if (right == primeCnt) {
					return;
				}
				sum += primeArr.get(right++);
			}
		}
	}
	
	public static void getPrime() {
		for (int i = 2; i <= N; ++i) {
			if (!isPrime[i]) {
				primeArr.add(i);
				for (int j = (i * 2); j <= N; j += i) {
					isPrime[j] = true;
				}
			}
		}
	}
}
