import java.util.Scanner;

public class 스도쿠검증_1974 {
	
	static int[][] arr;
	static boolean[] isUsed;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			arr = new int[9][9];
			
			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			// 3x3 체크
			int check1 = 1;
			for (int i = 0; i < 9; i += 3) {
				if (check1 == 0) {
					break;
				}
				
				for (int j = 0; j < 9; j += 3) {
					if (check1 == 0) {
						break;
					}
					
					isUsed = new boolean[10];
					
					check1 = check3x3(i, j);
				}
			}
			
			// 가로, 세로 체크
			int check2 = 1;
			for (int i = 0; i < 9; ++i) {
				if (check2 == 0) {
					break;
				}
				
				isUsed = new boolean[10];
				
				for (int j = 0; j < 9; ++j) {
					if (isUsed[arr[i][j]]) {
						check2 = 0;
						break;
					}					
					
					isUsed[arr[i][j]] = true;
				}
			}
			
			int check3 = 1;
			for (int i = 0; i < 9; ++i) {
				if (check3 == 0) {
					break;
				}
				
				isUsed = new boolean[10];
				
				for (int j = 0; j < 9; ++j) {					
					if (isUsed[arr[j][i]]) {
						check3 = 0;
						break;
					}
					
					isUsed[arr[j][i]] = true;
				}
			}
			
			System.out.println("#" + tc + " " + ((check1 == 1 && check2 == 1 && check3 == 1) ? 1 : 0));
		}
		
		sc.close();
	}
	
	public static int check3x3(int i, int j) {
		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 3; ++l) {
				if (isUsed[arr[i + k][j + l]]) {
					return 0;
				}
				
				isUsed[arr[i + k][j + l]] = true;
			}
		}
		
		return 1;
	}

}
