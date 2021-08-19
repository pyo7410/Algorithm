import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 일로만들기2_12852 {
	public static int N;
	public static int[] memo;
	public static int[] path;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memo = new int[N + 1];
		path = new int[N + 1];
		
		memo[1] = 0;
		path[1] = 0;
		for (int i = 2; i <= N; ++i) {
			int min = memo[i - 1];
			path[i] = i - 1;
			
			if (i % 2 == 0 && min > memo[i / 2]) {
				min = memo[i / 2];
				path[i] = i / 2;
			}
			
			if (i % 3 == 0 && min > memo[i / 3]) {
				min = memo[i / 3];
				path[i] = i / 3;
			}
			
			memo[i] = min + 1;
		}
		
		System.out.println(memo[N]);
		
		int idx = N;
		StringBuilder sb = new StringBuilder(N + " ");
		while (path[idx] != 0) {
			sb.append(path[idx] + " ");
			idx = path[idx];
		}
		System.out.println(sb);
	}
}
