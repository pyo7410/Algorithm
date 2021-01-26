import java.util.Scanner;

public class 시각덧셈_1976 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int minute = 0;
			
			for (int i = 0; i < 2; ++i) {
				int h = sc.nextInt();
				int m = sc.nextInt();
				
				minute += (h * 60) + m;
			}
			
			int hour = (minute / 60) % 12;
			if (hour == 0) {
				hour = 12;
			}
			
			minute %= 60;
			
			System.out.println("#" + tc + " " + hour + " " + minute);
		}
		
		sc.close();
	}
}
