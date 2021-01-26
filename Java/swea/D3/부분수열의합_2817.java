package D3;

import java.util.Scanner;

public class 부분수열의합_2817 {
	public static int[] arr;
	public static int n;
	public static int t;
	public static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			n = sc.nextInt();
			t = sc.nextInt();
			
			arr = new int[n];
			for (int i = 0; i < n; ++i) {
				arr[i] = sc.nextInt();
			}
			
			// 매번 정답을 새로 구해야하므로 0으로 초기화해야됨을 주의!
			answer = 0;
			solve(0, 0);
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
	
	public static void solve(int num, int i) {
		if (num == t) {
			answer++;
			return;
		}
		
		if (num > t || i >= n) {
			return;
		}
		
		// 현재 수를 선택
		solve(num + arr[i], i + 1);
		// 현재 수를 선택 안함
		solve(num, i + 1);
	}
}
