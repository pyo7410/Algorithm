import java.util.Scanner;

public class 날짜계산기_1948 {
	
	static int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int m1 = sc.nextInt();
			int d1 = sc.nextInt();
			int m2 = sc.nextInt();
			int d2 = sc.nextInt();
			
			int answer = 0;
			
			if (m1 == m2) {
				answer = d2 - d1 + 1;
			} else {
				for (int i = m1 + 1; i < m2; ++i) {
					answer += month[i];
				}
				
				answer += (month[m1] - d1 + 1);
				answer += d2;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}

}
