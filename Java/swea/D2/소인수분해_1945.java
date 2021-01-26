import java.util.Scanner;

public class 소인수분해_1945 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		int[] arr = {2, 3, 5, 7, 11};
		
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			System.out.print("#" + tc + " ");
			
			for (int i = 0; i < arr.length; ++i) {
				int cnt = 0;
				
				while (n % arr[i] == 0) {
					n /= arr[i];
					cnt++;
				}
				
				System.out.print(cnt + " ");
			}
			
			System.out.println();
			
		}
		
		sc.close();
	}
}
