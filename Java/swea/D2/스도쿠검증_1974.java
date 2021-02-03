package D2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 스도쿠검증_1974 {
	
	static int[][] sudoku;
	static HashSet<Integer> set = new HashSet<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sudoku = new int[9][9];
			
			for (int i = 0; i < 9; ++i) {
				String input = br.readLine();
				StringTokenizer st = new StringTokenizer(input, " ");
				for (int j = 0; j < 9; ++j) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = (check3x3() && checkRow() && checkColumn()) ? 1 : 0;
			
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	public static boolean check3x3() {
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				set.clear();
					
				for (int ii = 0; ii < 3; ++ii) {
					for (int jj = 0; jj < 3; ++jj) {
						set.add(sudoku[i + ii][j + jj]);
					}
				}
				
				if (set.size() < 9) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean checkRow() {
		for (int i = 0; i < 9; ++i) {
			set.clear();
			
			for (int j = 0; j < 9; ++j) {
				set.add(sudoku[i][j]);
			}
			
			if (set.size() < 9) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean checkColumn() {
		for (int i = 0; i < 9; ++i) {
			set.clear();
			
			for (int j = 0; j < 9; ++j) {
				set.add(sudoku[j][i]);
			}
			
			if (set.size() < 9) {
				return false;
			}
		}
		
		return true;
	}
}
