package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 대규의팬덤활동_7393 {
	public static int N;
	public static int[] memo = new int[101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = 0;
			
			// 고른 숫자의 비트자리수를 1로세팅
			// 0번쨰를 1로
			recur(0, 1, 1 << 1);
			// 0번쨰를 2로
			recur(0, 2, 1 << 2);
			// 0번쨰를 3로
			recur(0, 3, 1 << 3);
			recur(0, 4, 1 << 4);
			recur(0, 5, 1 << 5);
			recur(0, 6, 1 << 6);
			recur(0, 7, 1 << 7);
			recur(0, 8, 1 << 8);
			recur(0, 9, 1 << 9);
			
			
			sb.append("#").append(tc).append(" ").append("\n");
		}
		
		System.out.println(sb);
	}
	
	// pos번째 숫자를 num으로 선택하는 상태
	public static long recur(int pos, int num, int state) {
		// 숫자는 0 ~ 9만 쓰므로
		if (num < 0 || num > 9) {
			// 0 ~ 9의 범위를 벗어나므로 무효
			return 0;
		}
		
		if (pos == N - 1) {
			// 0 ~ 9의 비트가 전부 차면 1^10비트 -1이된다.
			if (state == (1 << 10) - 1) {
				// 끝까지 왔는데 모든 숫자를 다 썻다면.
				return 1;
			}
			else {
				// 끝까지 왔어도 모든 숫자를 다 못썻다면
				return 0;
			}
		}
		
		// 나보다 1 작은 수로 가는 경우, 나보다 1큰 수로 가는 경우
		
		// 나보다 하나 작은애의 flag를 켜서 간다.
		// -1인 경우 검사 필요
	
		// 나보다 하나 큰애의 flag를 켜서 간다.
		// 9보다 작거나같은지 검사 필요
		return recur(pos + 1, num - 1, state | (1 << (num - 1))) + recur(pos + 1, num + 1, state | (1 << (num + 1)));
	}
}
