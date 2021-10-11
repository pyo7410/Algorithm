package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 조합_5607 {
	public final static int mod = 1234567891;
	public static int N, R, answer;
	public static long[] factorial;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		// 팩토리얼 계산
		factorial = new long[1000001];
		factorial[1] = 1 % mod;
		for (int i = 2; i <= 1000000; ++i) {
			factorial[i] = (i * factorial[i - 1]) % mod;
		}
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			// 페르마의 소정리
			long top = factorial[N] % mod;
			long bottom = (factorial[N - R] * factorial[R]) % mod;
			
			long calcBottom = getPow(bottom, mod - 2);
			
			sb.append("#").append(tc).append(" ").append((top * calcBottom) % mod).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 거듭제곱 분할 정복
	public static long getPow(long num, int pow) {
		if (pow == 0) {
			return 1;
		}
		
		long result = getPow(num, pow / 2);
		
		if (pow % 2 == 0) {
			return (result * result) % mod;
		}
		else {
			return ((num * result) % mod * result) % mod;
		}
	}
}
