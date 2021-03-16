package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 행렬찾기_1258 {
	static class Info {
		int r;
		int c;
		
		public Info(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static int N, r, c;
	public static List<Info> answer;
	public static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N][N];
			answer = new ArrayList<Info>();
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; ++j) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					r = 0;
					c = 0;
					if (arr[i][j] > 0) {
						dfs(i, j, i, j);
						answer.add(new Info(r, c));
					}
				}
			}
			
			Collections.sort(answer, new Comparator<Info>() {

				@Override
				public int compare(Info o1, Info o2) {
					// TODO Auto-generated method stub
					int diff = (o1.c * o1.r) - (o2.c * o2.r);
					return (diff == 0) ? (o1.r - o2.r) : diff;
				}
				
			});
			
			sb.append("#").append(tc).append(" ").append(answer.size());
			for (Info i : answer) {
				sb.append(" ").append(i.r).append(" ").append(i.c);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int y, int x, int st_y, int st_x) {
		if (y > st_y && arr[y][st_x] == 0) {
			return;
		}
		
		arr[y][x] = -1;
		
		if (y == st_y) {
			c++;
		}
		
		if (x + 1 >= N || (x + 1 < N && arr[y][x + 1] == 0)) {
			r++;
			dfs(y + 1, st_x, st_y, st_x);
		}
		else {
			dfs(y, x + 1, st_y, st_x);			
		}
	}
}
