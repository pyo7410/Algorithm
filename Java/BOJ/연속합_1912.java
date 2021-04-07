import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합_1912 {
	public static int N;
	public static int[] arr = new int[100001];
	public static int[] memo = new int[100001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		memo[1] = arr[1];
		int answer = memo[1];
		
		for (int i = 2; i <= N; ++i) {
			memo[i] = arr[i];
			
			if (memo[i - 1] + arr[i] > memo[i]) {
				memo[i] = memo[i - 1] + arr[i];
			}
			
			answer = (answer < memo[i]) ? memo[i] : answer;
		}
		
		System.out.println(answer);
	}
}
