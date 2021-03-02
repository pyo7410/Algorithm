package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ladder2_1211 {
	public static int answer, maxCnt;
	public final static int MAX = 100;
	public static int[][] ladder = new int[MAX][MAX];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			br.readLine();
			
			for (int i = 0; i < MAX; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < MAX; ++j) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCnt = Integer.MAX_VALUE;
			
			for (int i = 0; i < MAX; ++i) {
				if (ladder[0][i] == 1) {
					go(i);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void go(int x) {
		int cnt = 0;
		int cur_x = x;
		
		for (int i = 0; i < MAX; ++i) {
			if (cur_x - 1 >= 0 && ladder[i][cur_x - 1] == 1) {
				do {
					cnt++;
					cur_x--;
				} while (cur_x - 1 >= 0 && ladder[i][cur_x - 1] == 1);
				
				continue;
			}
			else if (cur_x + 1 < MAX && ladder[i][cur_x + 1] == 1) {
				do {
					cnt++;
					cur_x++;
				} while (cur_x + 1 < MAX && ladder[i][cur_x + 1] == 1);
				
				continue;
			}

			cnt++;
		}
		
		if (maxCnt > cnt) {
			maxCnt = cnt;
			answer = x;
		}
	}
}
