package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 극한의청소작업_4530 {
	public static long answer;
	public static String A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			A = st.nextToken();
			B = st.nextToken();
			
			long tenA = convert(A);
			long tenB = convert(B);
			
			if (tenA < 0 && tenB > 0) {
				// 0층은 없으므로 1을 빼야한다.
				answer = tenB - tenA - 1;
			}
			else {
				// B는 A보다 항상 높으므로
				answer = tenB - tenA;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static long convert(String num) {
		boolean isMinus = false;
		int start = 0;
		
		// 음수일 경우 계산의 편의를 위해 isMinus 플래그를 true로 바꾸고
		// num의 시작을 '-'를 제외한 자리부터 하도록 바꿈
		if (num.charAt(0) == '-') {
			isMinus = true;
			start = 1;
		}
		
		long idx = 0;
		long result = 0;
		for (int i = num.length() - 1; i >= start; i--) {
			int cur = num.charAt(i) - '0'; 
			
			// 9진수로 계산할 때 4는 없는 숫자이므로
			// 4보다 큰 수들은 -1을 해준다.
			if (cur > 4) {
				cur -= 1;
			}
			
			// 해당 자리수의 9진수로 바꾸어준다.
			// n * (9 ^ idx)
			result += cur * (long)Math.pow(9, idx++);
		}
		
		// 음수 였다면 -를 붙여서 반환
		return isMinus ? -result : result;
	}
}
