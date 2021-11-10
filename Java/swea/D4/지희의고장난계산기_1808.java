package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 지희의고장난계산기_1808 {
	public static int X;
	public static int[] number, memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			number = new int[10];
			
			for (int i = 0; i < 10; ++i) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			X = Integer.parseInt(br.readLine());
			
			memo = new int[X + 10];
			
			for (int i = 0; i < 10; ++i) {
				if (number[i] == 1) {
					memo[i] = 1;
				}
			}
			
			go(X);
			
			// 마지막 +1은 '='을 추가해야하기 때문이다.
			sb.append("#").append(tc).append(" ").append(memo[X] == Integer.MAX_VALUE ? -1 : memo[X] + 1).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int go(int num) {
		if (memo[num] != 0) {
			return memo[num];
		}
		
		// 직접 누른거 안눌리면 최댓값
		memo[num] = count(num);
		
		for (int i = 1; i <= (int)Math.sqrt(num); ++i) {
			if (num % i == 0) {
				// 인수분해 시도
				// 분할정복
				int num1 = go(i);
				int num2 = go(num / i);
				
				// Integer.MAX_VALUE라면 안눌렸다는 것
				memo[num] = Math.min(memo[num], (num1 == Integer.MAX_VALUE || num2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : num1 + num2 + 1));
			}
		}
		
		return memo[num];
	}
	
	// 0은 아직 안한거
	// max는 안눌리는거
	// 값 도달하는 수
	public static int count(int num) { // 무조건 직접 누르는게 이득
		int cnt = 0;
		
		while (num > 0) {
			int tmp = num % 10;
			if (number[tmp] == 0) {
				return Integer.MAX_VALUE;
			}
			
			num /= 10;
			cnt++;
		}
		
		return cnt;
	}
}
