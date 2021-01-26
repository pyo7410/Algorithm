import java.util.Scanner;

public class RC카_1940 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			int answer = 0;
			int total_speed = 0;
			for (int i = 0; i < n; ++i) {
				int command = sc.nextInt();
				
				if (command == 0) {
					answer += total_speed;
					continue;
				}
				
				int speed = sc.nextInt();
				
				if (command == 1) {
					total_speed += speed;
					answer += total_speed;
				} else {
					total_speed -= speed;
					
					if (total_speed < 0) {
						total_speed = 0;
					}
					
					answer += total_speed;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}

}
