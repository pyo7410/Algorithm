package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 격자판의숫자이어붙이기_2819 {
	public static int[][] board = new int[4][4];
	public static Set<String> set;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			for (int i = 0; i < 4; ++i) {
				st = new StringTokenizer(br.readLine());
				 
				for (int j = 0; j < 4; ++j) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			set = new HashSet<String>();
			
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					go(i, j, 0, Integer.toString(board[i][j]));
				}
			}
			
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void go(int y, int x, int cnt, String s) {
		if (cnt == 6) {
			if (s.length() == 7) {
				set.add(s);
			}
			
			return;
		}
		
		if (y - 1 >= 0) {
			go(y - 1, x, cnt + 1, s + Integer.toString(board[y - 1][x]));
		}
		if (y + 1 < 4) {
			go(y + 1, x, cnt + 1, s + Integer.toString(board[y + 1][x]));
		}
		if (x - 1 >= 0) {
			go(y, x - 1, cnt + 1, s + Integer.toString(board[y][x - 1]));
		}
		if (x + 1 < 4) {
			go(y, x + 1, cnt + 1, s + Integer.toString(board[y][x + 1]));
		}
	}
}
