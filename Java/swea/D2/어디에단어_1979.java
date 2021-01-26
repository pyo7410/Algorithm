import java.util.Scanner;

public class 어디에단어_1979 {
	static int[][] arr;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {			
			int answer = 0;
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			arr = new int[n][n];
			
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i < n; ++i) {
				int r_cnt = 0, c_cnt = 0;
				for (int j = 0; j < n; ++j) {
					if (arr[i][j] == 1) {
						c_cnt++;
					} else {
						if (c_cnt == k) {
							answer++;
						}
						c_cnt = 0;
					}
					
					if (arr[j][i] == 1) {
						r_cnt++;
					} else {
						if (r_cnt == k) {
							answer++;
						}
						r_cnt = 0;
					}
				}
				
				if (c_cnt == k) {
					answer++;
				}
				
				if (r_cnt == k) {
					answer++;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
}
