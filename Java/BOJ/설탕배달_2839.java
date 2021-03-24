import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 설탕배달_2839 {
	public static int N;
	public static int[] memo = new int[5001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Arrays.fill(memo, Integer.MAX_VALUE);
		
		memo[3] = 1;
		memo[5] = 1;
		for (int i = 6; i <= N; ++i) {
			int min = Math.min(memo[i - 3], memo[i - 5]);
			
			if (min != Integer.MAX_VALUE) {
				memo[i] = min + 1;				
			}
		}
		
		System.out.println((memo[N] == Integer.MAX_VALUE) ? -1 : memo[N]);
		
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		
		// 3a + 5b = n 임을 생각하라
		// n / 3 반복문 하나와, n / 5 반복문
		// 즉, 2중 반복문을 사용해서 a, b를 계속 구해서
		// 최소 값을 갱신하면 된다.	
		/*
		int answer = -1;		
		for (int i = 0; i <= n / 3; ++i) {
			for (int j = 0; j <= n / 5; ++j) {
				if ((i * 3) + (j * 5) == n) {
					if (answer == -1) {
						answer = i + j;
					}
					else {
						answer = Math.min(answer, i + j);
					}
				}
			}
		}
		
		System.out.println(answer);
		*/
		
//		int cnt = 0;
//		while (true) {
//			if (n % 5 == 0) {
//				System.out.println(n / 5 + cnt);
//				break;
//			}
//			else {
//				cnt++;
//				n -= 3;
//			}
//			
//			if (n < 0) {
//				System.out.println(-1);
//				break;
//			}
//		}
//		
//		sc.close();
	}
}
