package D3;

import java.util.Arrays;
import java.util.Scanner;

public class 진기의최고급붕어빵_1860 {
	static int[] people;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			
			people = new int[n];
			for (int i = 0; i < n; ++i) {
				people[i] = sc.nextInt();
			}
			
			int total_bread = 0;
			int idx = 0;
			Arrays.sort(people);
			
			boolean answer = true;
			for (int i = 0; i < n; ++i) {
				// 자기자신은 빼면 안된다!
				// 아래서 0보다 작을 때 검사하므로 1개만 있어도 통과!
				total_bread = (people[i] / m * k) - i;
				
//				if (i > 0 && i % m == 0) {
//					total_bread += k;
//				}
//				
//				if (i == people[idx]) {
//					if (total_bread <= 0) {
//						answer = false;
//						break;
//					}
//					
//					idx++;
//					total_bread--;
//				}
				
				if (total_bread < 0) {
					answer = false;
					break;
				}
			}
			
			System.out.println("#" + tc + " " + (answer ? "Possible" : "Impossible"));
		}
    }
}
