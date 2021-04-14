import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사_14501 {
	public static int N, ans;
	public static int[] T = new int[16];
	public static int[] P = new int[16];
	public static int[] memo = new int[17];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;		
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			int day = i + T[i];
			
			// N번째 날의 상담 기간이 하루인 경우
			// 또는 day가 N + 1인 경우
			// N번째 날까지 처리가 가능하므로
			// N + 1일까지는 봐야한다.
			if (day <= N + 1) {
				// i번째날 상담을 한 경우
				memo[day] = Math.max(memo[i] + P[i], memo[day]);
				answer = (answer < memo[day]) ? memo[day] : answer;
			}
			
			// i번째날 상담을 안한경우
			// 무조건 수행해 주어야한다.
			memo[i + 1] = Math.max(memo[i], memo[i + 1]);
			answer = (answer < memo[i +1 ]) ? memo[i + 1] : answer;
		}
		
		// 완탐
//		go(0, 0);
		
		System.out.println(answer);
	}
	
	public static void go(int day, int sum)
	{
	    if (day == N)
	    {
	        ans = Math.max(ans, sum);        
	        return;
	    }
	    if (day > N)
	    {
	        return;
	    }
	    go(day + T[day], sum + P[day]); // 상담 O
	    go(day + 1, sum);               // 상담 x
	}
}
