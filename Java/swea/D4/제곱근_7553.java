package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 제곱근_7553 {
	public static long N, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Long.parseLong(br.readLine());
			
			long start = 0;
			long end = N;
			
			while (start <= end) {
				long mid = (start + end) / 2;
				
				long num = (long) Math.sqrt(N);
				
				if (mid == num) {
					answer = mid;
					break;
				}
				else if (mid < num) {
					start = mid + 1;
				}
				else if (mid > num) {
					end = mid - 1;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
