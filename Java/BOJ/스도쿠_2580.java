import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도쿠_2580 {
	public static int[][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		board = new int[9][9];
		
		for (int i = 0; i < 9; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		go(0, 0);
	}
	
	public static void go(int y, int x) {
		if (y == 9) {
			StringBuilder sb = new StringBuilder("");
			
			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb);
			
			System.exit(0);
		}
		
		if (x == 9) {
			go(y + 1, 0);
			return;
		}
		
		if (board[y][x] == 0) {
			for (int i = 1; i <= 9; ++i) {
				if (!row(y, i) || !col(x, i) || !sqaure(y, x, i)) {
					continue;
				}
				
				board[y][x] = i;
				go(y, x + 1);
			}
			
			board[y][x] = 0;
		}
		else {
			go(y, x + 1);
		}
	}
	
	// 가로 검사
	public static boolean row(int y, int num) {
		for (int i = 0; i < 9; ++i) {
			if (board[y][i] == num) {
				return false;				
			}
		}
		
		return true;
	}
	
	// 세로 검사
	public static boolean col(int x, int num) {
		for (int i = 0; i < 9; ++i) {
			if (board[i][x] == num) {
				return false;
			}
		}
		
		return true;
	}
	
	// 사각형 검사
	public static boolean sqaure(int y, int x, int num) {
		int ny = (y / 3) * 3;
		int nx = (x / 3) * 3;
		
		for (int i = ny; i < ny + 3; ++i) {
			for (int j = nx; j < nx + 3; ++j) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}
		
		return true;
	}
}
