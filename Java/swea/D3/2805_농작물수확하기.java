package D3;

import java.util.Scanner;

public class 농작물수확하기_2805 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			int N = sc.nextInt(); // 농작의 크기 (<= 49)
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				String str = sc.next();
				// arr의 i번째 행에, 한 글자씩 숫자로 넣는다.
				for (int j = 0; j < N; ++j) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}
			
			int answer = 0, area = 0, mid = (N / 2);
			for (int i = 0; i < N; ++i) {
				int st = mid - area;
				int en = mid + area;
				for (int j = st; j <= en; ++j) {
					answer += arr[i][j];
				}
				
				if (i >= mid) {
					area--;
				} else {
					area++;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
}
