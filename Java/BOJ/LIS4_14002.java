import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS4_14002 {
	public static int N, answer;
	public static StringBuilder sb;
	public static int[] A, memo, subsequence;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;		
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N + 1];
		memo = new int[N + 1];
		subsequence = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; ++i) {
			memo[i] = 1;
			subsequence[i] = i;
			
			for (int j = 1; j < i; ++j) {
				if (A[j] < A[i] && memo[j] + 1 > memo[i]) {
					memo[i] = memo[j] + 1;
					subsequence[i] = j;
				}
			}
		}
		
		int maxIdx = 1;
		for (int i = 1; i <= N; ++i) {
			if (answer < memo[i]) {
				answer = memo[i];
				maxIdx = i;
			}
		}
		
		sb = new StringBuilder("");
		print(maxIdx);
		
		System.out.println(answer);
		System.out.println(sb);
	}
	
	public static void print(int cur) {
		if (cur != subsequence[cur]) {
			print(subsequence[cur]);
		}
		
		sb.append(A[cur]).append(" ");
	}
}
