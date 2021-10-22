package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합_5604 {
	public static long A, B, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			
			answer = 0;
			
			// 현재 자리 수
			long cur = 1;
			
			while (A <= B) {
				// A의 맨 뒤자리 즉, 1의 자리의 수가 0이 될때까지 반복
				while (A % 10 != 0 && A <= B) {
					calc(A, cur);
					A++;
				}
				
				if (A > B || (A == 0 && B == 0)) {
					break;
				}
				
				// B의 맨 뒤자리 즉, 1의 자리의 수가 9가 될때까지 반복
				while (B % 10 != 9 && A <= B) {
					calc(B, cur);
					B--;
				}
				
				// 1의 자리수를 검사했다면 10의 자리수를 검사하기위함
				A /= 10;
				B /= 10;
				
				long cnt = (B - A + 1) * cur;
				for (int i = 0; i < 10; ++i) {
					answer += (cnt * i);
				}
				
				// 다음 자리수로 바꿈
				cur *= 10;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void calc(long num, long cur) {
		while (num > 0) {
			answer += (num % 10) * cur;
			num /= 10;
		}
	}
}
