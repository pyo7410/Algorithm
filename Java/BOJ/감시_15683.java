import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시_15683 {
	public static class Info {
		int y, x, type;

		public Info(int y, int x, int type) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}
	
	public static int N, M, answer;
	public static List<Info> cameras;
	public static int[] cameraDir;
	public static int[][] office, copyOffice;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		copyOffice = new int[N][M];
		cameras = new ArrayList<Info>();
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				office[i][j] = Integer.parseInt(st.nextToken());
				
				if (office[i][j] > 0 && office[i][j] < 6) {
					cameras.add(new Info(i, j, office[i][j]));
				}
			}
		}
		
		cameraDir = new int[cameras.size()];
		answer = Integer.MAX_VALUE;
		
		rotate(0);
		
		System.out.println(answer);
	}
	
	
	// 우 상 좌 하
	public static int dx[] = {1, 0, -1, 0};
	public static int dy[] = {0, -1, 0, 1};
	
	public static void copy(int[][] origin, int[][] target) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				target[i][j] = origin[i][j];
			}
		}
	}
	
	public static void rotate(int cnt) {
		if (cnt == cameras.size()) {
			copy(office, copyOffice);
			
			for (int i = 0; i < cameras.size(); ++i) {
				Info info = cameras.get(i);
				
				if (info.type == 1) {
					go(info.y, info.x, cameraDir[i]);								
				}
				else if (info.type == 2) {
					for (int k = 0; k < 2; ++k) {
						int dir = (cameraDir[i] + (k * 2)) % 4;
						go(info.y, info.x, dir);
					}
				}
				else if (info.type == 3) {
					for (int k = 0; k < 2; ++k) {
						int dir = (cameraDir[i] + k) % 4;
						go(info.y, info.x, dir);
					}
				}
				else if (info.type == 4) {
					for (int k = 0; k < 3; ++k) {
						int dir = (cameraDir[i] + k) % 4;
						go(info.y, info.x, dir);
					}
				}
				else if (info.type == 5) {
					for (int k = 0; k < 4; ++k) {
						go(info.y, info.x, k);
					}
				}
			}
			
			int blindArea = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (copyOffice[i][j] == 0) {
						blindArea++;
					}
				}
			}
			
			answer = (answer > blindArea) ? blindArea : answer;
			
			return;
		}
		
		for (int i = 0; i < 4; ++i) {
			cameraDir[cnt] = i;
			rotate(cnt + 1);
		}
	}
	
	public static void go(int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		while (ny >= 0 && nx >= 0 && ny < N && nx < M) {
			if (copyOffice[ny][nx] == 6) {
				return;
			}
			
			if (copyOffice[ny][nx] == 0) {
				copyOffice[ny][nx] = -1;
			}
			
			ny += dy[dir];
			nx += dx[dir];
		}
	}
}
