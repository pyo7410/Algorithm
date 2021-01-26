import java.util.Arrays;
import java.util.Scanner;

public class 중간평균값_1984 {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			arr = new int[10];
			
			for (int i = 0; i < 10; ++i) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);
			
			int answer = 0;
			for (int i = 1; i < 9; ++i) {
				answer += arr[i];
			}
			
			answer = (Math.round((float)answer / 8f));
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
}
