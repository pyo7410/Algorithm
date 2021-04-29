import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS_9251 {
	public static String S1, S2;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S1 = br.readLine();
		S2 = br.readLine();
		
		int len1 = S1.length();
		int len2 = S2.length();
		
		memo = new int[len1 + 1][len2 + 1];
		
		for (int i = 1; i <= len1; ++i) {
			for (int j = 1; j <= len2; ++j) {
				
				if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
					// 문자가 같다면 이전 문자까지의 LCS에 +1을 해주어야하는데
					// S1 문자열일 경우 현재 문자의 이전 문자는 i - 1이고
					// S2 문자열일 경우 현재 문자의 이전 문자는 j - 1이 되므로
					// S1, S2 둘다의 이전 문자의 LCS는 memo[i - 1][j - 1]이 된다.
					memo[i][j] = memo[i - 1][j - 1] + 1;
				}
				else {
					// 문자가 다를경우 S1 문자열의 이전 문자까지의 LCS 즉, memo[i - 1][j]와
					// S2 문자열의 이전 문자까지의 LCS 즉, memo[i][j - 1] 중 큰 값을 선택하면 된다.
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
				}
			}
		}
		
		System.out.println(memo[len1][len2]);
	}
}
