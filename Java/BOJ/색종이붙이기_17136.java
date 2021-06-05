import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_17136 {
	public static int answer;
	public static int[][] paper = new int[10][10];
	public static int[] colorPapers = {5, 5, 5, 5, 5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 10; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; ++j) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		findPaper(0, 0, 0);
		
		System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
	}
	
	// 붙일 수 있는 모든 경우를 검사하면서 붙인 색종이의 최소값을 구하는 함수
	public static void findPaper(int y, int x, int cnt) {
		// 행단위 검사 후 열단위를 검사하므로
		// 마지막 행의 마지막 열까지는 검사해야한다.
		if (y >= 9 && x > 9) {
			answer = (answer > cnt) ? cnt : answer;
			return;
		}
		
		// 최소값을 넘어갔으므로 더이상 조사할 필요가 없다.
		if (answer <= cnt) {
			return;
		}
		
		// 다음 행 검사
		if (x > 9) {
			findPaper(y + 1, 0, cnt);
			return;
		}
		
		if (paper[y][x] == 1) {
			for (int k = 4; k >= 0; k--) {
				if (colorPapers[k] > 0 && isPaste(y, x, k)) {
					// 우선 종이를 붙여보고 다음 1을 찾아 조사
					colorPapers[k]--;
					pasteColorPaper(y, x, k, 0);
					findPaper(y, x + 1, cnt + 1);
					
					// 더 작은 종이를 붙여서 최소가 나올 수 있으므로
					// 붙였던 종이를 떼고 다시 조사할 수 있게 한다.
					colorPapers[k]++;
					// 위에서 크기만큼의 색종이를 붙였으므로
					// 그 크기만큼 다시 1로 만들면 원상복구가 된다!
					pasteColorPaper(y, x, k, 1);
				}
			}
			
			return;
		}
		
		// 1을 못 찾았으므로
		// 다음 열 검사
		findPaper(y, x + 1, cnt);
	}
	
	// 색종이를 붙였다 때는 함수
	public static void pasteColorPaper(int y, int x, int size, int value) {
		for (int i = y; i <= y + size; ++i) {
			for (int j = x; j <= x + size; ++j) {
				paper[i][j] = value;
			}
		}
	}
	
	// 색종이를 붙일 수 있는지 검사하는 함수
	public static boolean isPaste(int y, int x, int size) {
		if (y + size >= 10 || x + size >= 10) {
			return false;
		}
		
		for (int i = y; i <= y + size; ++i) {
			for (int j = x; j <= x + size; ++j) {
				if (paper[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
}
