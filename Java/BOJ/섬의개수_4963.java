import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 섬의개수_4963 {
	public static int w, h;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new int[h][w];
			
			for (int i = 0; i < h; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			for (int i = 0; i < h; ++i) {
				for (int j = 0; j < w; ++j) {
					if (map[i][j] == 1) {
						answer++;
						go(i, j);
					}
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
	public static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
	
//	public static void go(int y, int x) {
//		map[y][x] = 0;
//		
//		// 상
//		if (y - 1 >= 0) {
//			if (map[y - 1][x] == 1) {
//				go(y - 1, x);				
//			}
//			
//			// 대각선 처리
//			if (x - 1 >= 0 && map[y - 1][x - 1] == 1) {
//				go(y - 1, x - 1);
//			}
//			if (x + 1 < w && map[y - 1][x + 1] == 1) {
//				go(y - 1, x + 1);
//			}
//		}
//		
//		// 하
//		if (y + 1 < h) {
//			if (map[y + 1][x] == 1) {
//				go(y + 1, x);				
//			}
//			
//			// 대각선 처리
//			if (x - 1 >= 0 && map[y + 1][x - 1] == 1) {
//				go(y + 1, x - 1);
//			}
//			if (x + 1 < w && map[y + 1][x + 1] == 1) {
//				go(y + 1, x + 1);
//			}
//		}
//		
//		// 좌
//		if (x - 1 >= 0 && map[y][x - 1] == 1) {
//			go(y, x - 1);
//		}
//		
//		// 우
//		if (x + 1 < w && map[y][x + 1] == 1) {
//			go(y, x + 1);
//		}
//	}
	
//	public static void go(int y, int x) {
//		map[y][x] = 0;
//		
//		for (int i = 0; i < 8; ++i) {
//			int ny = y + dy[i];
//			int nx = x + dx[i];
//			
//			if (ny < 0 || nx < 0 || ny >= h || nx >= w) {
//				continue;
//			}
//			
//			if (map[ny][nx] == 1) {
//				go(ny, nx);
//			}
//		}
//	}
	
	static class Info {
		public int i, j;

		public Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void go(int i, int j) {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(i, j));
		map[i][j] = 0;
		
		while (!q.isEmpty()) {
			Info cur = q.poll();
			
			for (int k = 0; k < 8; ++k) {
				int ny = cur.i + dy[k];
				int nx = cur.j + dx[k];
				
				if (ny < 0 || nx < 0 || ny >= h || nx >= w) {
					continue;
				}
				
				if (map[ny][nx] == 1) {
					q.offer(new Info(ny, nx));
					map[ny][nx] = 0;
				}
			}
		}
	}
}
