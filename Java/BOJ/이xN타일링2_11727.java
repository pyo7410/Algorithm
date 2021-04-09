import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이xN타일링2_11727 {
	public static int N;
	public static int[] memo = new int[1001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		memo[0] = 1;
		memo[1] = 1;
		for (int i = 2; i <= N; ++i) {
			memo[i] = (memo[i - 1] % 10007) + ((2 * memo[i - 2]) % 10007); 
		}
		
		System.out.println(memo[N] % 10007);
	}
}
