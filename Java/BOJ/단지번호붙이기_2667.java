import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 단지번호붙이기_2667 {
	public static int N, cnt;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			for (int j = 0; j < N; ++j) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		ArrayList<Integer> total = new ArrayList<Integer>();
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (map[i][j] == 1) {
					cnt = 0;
					go(i, j);
					total.add(cnt);
				}
			}
		}
		
		Collections.sort(total);
		
		System.out.println(total.size());
		for (int num : total) {
			System.out.println(num);
		}
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	public static void go(int y, int x) {
		map[y][x] = 0;
		cnt++;
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}
			
			if (map[ny][nx] == 1) {
				go(ny, nx);
			}
		}
	}
}
