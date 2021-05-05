import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class LBS_11054 {
	public static int N, answer;
	public static int[] A = new int[1001];
	public static int[] memo= new int[1001];
	public static int[] memo_reverse = new int[1001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; ++i) {
			memo[i] = 1;
			
			for (int j = 1; j < i; ++j) {
				if (A[i] > A[j] && memo[i] < memo[j] + 1) {
					memo[i] = memo[j] + 1;
				}
			}
		}
		
		for (int i = N; i >= 1; i--) {
			memo_reverse[i] = 1;
			
			for (int j = N; j > i; j--) {
				if (A[i] > A[j] && memo_reverse[i] < memo_reverse[j] + 1) {
					memo_reverse[i] = memo_reverse[j] + 1;
				}
			}
		}
		
		for (int i = 1; i <= N; ++i) {
			answer = (answer < memo[i] + memo_reverse[i] - 1) ? memo[i] + memo_reverse[i] - 1 : answer;
		}
		
		System.out.println(answer);
	}
}
