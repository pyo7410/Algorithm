import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼_20056 {
	public static class Fireball {
		int y, x, m, s, d;

		public Fireball(int y, int x, int m, int s, int d) {
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static class FireballMap {
		int m, s, d, cnt;
		boolean isEqualDir;
		
		public FireballMap(boolean isEqualDir, int m, int s, int d, int cnt) {
			this.isEqualDir = isEqualDir;
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = cnt;
		}
	}
	public static int N, M, K;
	public static Queue<Fireball> fireballQueue;
	public static FireballMap[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		fireballQueue = new LinkedList<Fireball>();
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			fireballQueue.offer(new Fireball(r, c, m, s, d));
		}
		
		while (K-- > 0) {
			initMap();
			moveFireball();
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (map[i][j].cnt == 0) {
						continue;
					}
					
					if (map[i][j].cnt > 1) {
						splitFireball(i, j);
						continue;
					}
					
					fireballQueue.offer(new Fireball(i, j, map[i][j].m, map[i][j].s, map[i][j].d));
				}
			}
		}
		
		int answer = 0;
		while (!fireballQueue.isEmpty()) {
			Fireball fireball = fireballQueue.poll();
			answer += fireball.m;
		}
		
		System.out.println(answer);
	}
	
	public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void moveFireball() {
		while (!fireballQueue.isEmpty()) {
			Fireball fireball = fireballQueue.poll();
			
			int ny = fireball.y;
			int nx = fireball.x;
			for (int j = 0; j < fireball.s; ++j) {
				ny += dy[fireball.d];
				nx += dx[fireball.d];
				
				ny = (ny < 0) ? N - 1 : ((ny >= N) ? 0 : ny);
				nx = (nx < 0) ? N - 1 : ((nx >= N) ? 0 : nx);
			}
			
			map[ny][nx].cnt++;
			map[ny][nx].m += fireball.m;
			map[ny][nx].s += fireball.s;
			
			if (map[ny][nx].cnt > 1) {
				if (map[ny][nx].isEqualDir) {
					int dir = map[ny][nx].d + fireball.d;
					map[ny][nx].isEqualDir = (dir % 2 == 0) ? true : false;
				}
			}
			else if (map[ny][nx].cnt == 1) {
				map[ny][nx].d = fireball.d;
			}
		}
	}
	
	public static void splitFireball(int y, int x) {
		int m = map[y][x].m / 5;
		int s = map[y][x].s / map[y][x].cnt;
		
		if (m <= 0) {
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (map[y][x].isEqualDir) {
				fireballQueue.offer(new Fireball(y, x, m, s, 2 * i));
			}
			else {
				fireballQueue.offer(new Fireball(y, x, m, s, (2 * i) + 1));
			}
		}
	}
	
	public static void initMap() {
		map = new FireballMap[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				map[i][j] = new FireballMap(true, 0, 0, -1, 0);
			}
		}
	}
}
