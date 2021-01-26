import java.util.Arrays;
import java.util.Scanner;

public class 숫자정렬_1966 {
	
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			arr = new int[n];
			
			for (int i = 0; i < n; ++i) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);
			
			System.out.print("#" + tc + " ");
			
			for (int i = 0; i < n; ++i) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}

}
