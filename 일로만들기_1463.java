import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 일로만들기_1463 {
	public static int N;
	public static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memo = new int[N + 1];
		
		memo[1] = 0;
		for (int i = 2; i <= N; ++i) {
			int min = memo[i - 1];
			
			if (i % 2 == 0) {
				min = (min > memo[i / 2]) ? memo[i / 2] : min;
			}
			
			if (i % 3 == 0) {
				min = (min > memo[i / 3]) ? memo[i / 3] : min;
			}
			
			memo[i] = min + 1;
		}
		
		System.out.println(memo[N]);
	}
}
