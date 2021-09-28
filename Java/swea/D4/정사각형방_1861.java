package D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정사각형방_1861 {
	public static int N, answer, roomNum;
	public static int[][] room;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
 	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			answer = 0;
			roomNum = 987654321;
			room = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; ++j) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
                    // 처음 방도 포함해야하므로 +1
					int cnt = dfs(i, j) + 1;
					
					if (answer < cnt) {
						answer = cnt;
						roomNum = room[i][j];
					}
					else if (answer == cnt) {
						roomNum = Math.min(roomNum, room[i][j]);
					}
				}
			}
		
			sb.append("#").append(tc).append(" ");
			sb.append(roomNum).append(" ").append(answer);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	public static int dfs(int y, int x) {
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			if (room[ny][nx] - room[y][x] == 1) {
                return dfs(ny, nx) + 1;
			}			
		}
		return 0;
	}
	
	public static int start;
	public static void dfs2(int y, int x, int cnt) {
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			if (room[ny][nx] - room[y][x] == 1) {
				dfs2(ny, nx, cnt);
			}			
		}
		
		if (answer < cnt) {
			answer = cnt;
			roomNum = start;
		}
		else if (answer == cnt) {
			roomNum = Math.min(roomNum, start);
		}
	}
}
