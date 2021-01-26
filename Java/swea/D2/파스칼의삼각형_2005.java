import java.util.Scanner;

public class 파스칼의삼각형_2005 {
	static int[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			arr = new int[n][n];
			
			for (int i = 0; i < n; ++i) {
				arr[i][0] = 1;
			}
			
			for (int i = 1; i < n; ++i) {
				for (int j = 1; j <= i; ++j) {
					arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
				}
			}
			
			System.out.println("#" + tc + " " );
			
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j <= i; ++j) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		sc.close();
	}
}
