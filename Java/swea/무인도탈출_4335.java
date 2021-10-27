import java.util.Scanner;

public class 무인도탈출_4335 {
	public static int N, answer;
	public static boolean[] isUsed;
	public static int[][] boxList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; ++tc) {
			N = sc.nextInt();
			
			isUsed = new boolean[N];
			boxList = new int[N][3];
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < 3; ++j) {
					boxList[i][j] = sc.nextInt();
				}
			}
			
			calc(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
	
	
	public static void calc(int before_x, int before_y, int result) {
		if (result > answer) {
			answer = result;
		}
		
		for (int i = 0; i < N; ++i) {
			if (isUsed[i]) {
				continue;
			}
			
			int max = 0;
			for (int j = 0; j < 3; ++j) {
				int height = boxList[i][j];
				int cur_x = boxList[i][(j + 1) % 3];
				int cur_y = boxList[i][(j + 2) % 3];
				
				if (cur_x <= before_x && cur_y <= before_y) {
					isUsed[i] = true;
					calc(cur_x, cur_y, result + height);
					isUsed[i] = false;
				}
				else if (cur_x <= before_y && cur_y <= before_x) {
					isUsed[i] = true;
					calc(cur_y, cur_x, result + height);
					isUsed[i] = false;
				}
			}
		}
	}
}
