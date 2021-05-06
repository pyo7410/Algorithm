import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰증가부분수열_11055 {
	public static int N, answer;
	public static int[] A = new int[1001];
	public static int[] memo = new int[1001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; ++i) {
			memo[i] = A[i];
			
			for (int j = 1; j < i; ++j) {
				if (A[i] > A[j] && memo[i] < memo[j] + A[i]) {
					memo[i] = memo[j] + A[i];
				}
			}
		}
		
		for (int i = 1; i <= N; ++i) {
			answer = (answer < memo[i]) ? memo[i] : answer;
		}
		
		System.out.println(answer);
	}
}
