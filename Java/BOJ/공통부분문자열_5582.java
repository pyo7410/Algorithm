import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class 공통부분문자열_5582 {
	public static int answer;
	public static String S1, S2;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S1 = br.readLine();
		S2 = br.readLine();
		
		memo = new int[S2.length() + 1][S1.length() + 1];
		
		for (int i = 0; i < S2.length(); ++i) {
			for (int j = 0; j < S1.length(); ++j) {				
				if (S2.charAt(i) == S1.charAt(j)) {
					memo[i + 1][j + 1] = memo[i][j] + 1;
					answer = (answer < memo[i + 1][j + 1]) ? memo[i + 1][j + 1] : answer;
				}
			}
		}
		
		System.out.println(answer);
	}
}
