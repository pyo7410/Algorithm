import java.util.Scanner;

public class 두_개의_숫자열_1959 {

	static int[] A;
	static int[] B;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			A = new int[n];
			B = new int[m];
			
			for (int i = 0; i < n; ++i) {
				A[i] = sc.nextInt();
			}
			
			for (int i = 0; i < m; ++i) {
				B[i] = sc.nextInt();
			}
			
			int max_idx = n, min_idx = m;
			if (n < m) {
				max_idx = m;
				min_idx = n;
			}
			
			int answer = -1;
			for (int i = 0; i <= max_idx - min_idx; ++i) {
				int sum = 0;
				for (int j = 0; j < min_idx; ++j) {
					if (max_idx == n) {
						sum += (A[i + j] * B[j]);
					} else {
						sum += (A[j] * B[j + i]);
					}
				}
				
				answer = Math.max(answer, sum);
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}

}
