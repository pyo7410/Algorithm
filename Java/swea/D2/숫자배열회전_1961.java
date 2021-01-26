import java.util.Scanner;

public class 숫자배열회전_1961 {
	static int[][] arr;
	static int[][] temp;
	static String[][] answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			arr = new int[n][n];
			temp = new int[n][n];
			answer = new String[n][3];
			
			// 문자열 배열 초기화
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < 3; ++j) {
					answer[i][j] = "";
				}
			}
			
			// 배열 입력
			for (int i = 0; i < n ; ++i) {
				for (int j = 0; j < n; ++j) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			// 90도 회전
			for (int k = 0; k < 3; ++k) {
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < n; ++j) {
						temp[j][(n - 1) - i] = arr[i][j];
					}
				}
				
				// 임시 배열에 회전한 값을 넣어 회전을 완료한 후
				// 다시 원래 배열에 회전된 값을 넣고 정답에 추가
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < n; ++j) {
						arr[i][j] = temp[i][j];
						answer[i][k] += temp[i][j];
					}
				}
			}
			
			System.out.println("#" + tc);
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < 3; ++j) {
					System.out.print(answer[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		sc.close();
	}
}
