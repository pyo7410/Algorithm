import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩_6593 {
	public static class Info {
		int x, y, z, time;

		public Info(int x, int y, int z, int time) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.time = time;
		}
	}
	
	public static int L, R, C, answer;
	public static Info startInfo;
	public static StringBuilder sb;
	public static boolean[][][] visited;
	public static char[][][] building;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		sb = new StringBuilder("");
		
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			
			visited = new boolean[L][R][C];
			building = new char[L][R][C];
			
			for (int i = 0; i < L; ++i) {
				for (int j = 0; j < R; ++j) {
					building[i][j] = br.readLine().toCharArray();
					
					for (int k = 0; k < C; ++k) {
						if (building[i][j][k] == 'S') {
							// info의 순서가 x y z 임을 주의
							startInfo = new Info(k, j, i, 0);
							break;
						}
					}
				}
				
				br.readLine();
			}
			
			bfs();
		}
		
		System.out.println(sb);
	}
	
	public static int[] dz = {0, 0, 0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0, 0, 0};
	public static int[] dx = {0, 0, -1, 1, 0, 0};
	
	public static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(startInfo.x, startInfo.y, startInfo.z, 0));
		visited[startInfo.z][startInfo.y][startInfo.x] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			if (building[info.z][info.y][info.x] == 'E') {
				sb.append("Escaped in ").append(info.time).append(" minute(s).\n");
				return;
			}
			
			for (int i = 0; i < 6; ++i) {
				int nz = info.z + dz[i];
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (nz < 0 || ny < 0 || nx < 0 || nz >= L || ny >= R || nx >= C) {
					continue;
				}
				
				if (!visited[nz][ny][nx] && building[nz][ny][nx] != '#') {
					q.offer(new Info(nx, ny, nz, info.time + 1));
					visited[nz][ny][nx] = true;
				}
			}
		}
		
		sb.append("Trapped!\n");
	}
}
