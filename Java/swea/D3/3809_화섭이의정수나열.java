package D3;

import java.util.Scanner;

public class 화섭이의정수나열_3809 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < n; ++i) {
				sb.append(sc.nextInt());
			}
			
			String str = sb.toString();
			
			for (int i = 0; ; ++i) {
				// 만약 문자열에 해당 숫자가 포함되지 않았다면
				if (!str.contains(Integer.toString(i))) {
					System.out.println("#" + tc + " " + i);
					break;
				}
			}
		}
		
		sc.close();
	}
}
