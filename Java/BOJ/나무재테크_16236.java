import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나무재테크_16236 {
	public static class Info implements Comparable<Info> {
		int x, y, age;

		public Info(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
	public static int N, M, K;
	
	public static int[] dy = {1,-1,0,0,1,-1,1,-1};
	public static int[] dx = {0,0,1,-1,1,1,-1,-1};
	
	public static int[][] A;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N + 1][N + 1];
		map = new int[N + 1][N + 1];
		
		PriorityQueue<Info> infos = new PriorityQueue<Info>();
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 1; j <= N; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			infos.offer(new Info(x, y, age));
		}
		
		for (int year = 0; year < K; ++year) {
			Queue<Info> q = new LinkedList<Info>();
			Queue<Info> die = new LinkedList<Info>();
			Queue<Info> spread = new LinkedList<Info>();
			
			// 봄
			while (!infos.isEmpty()) {
				Info info = infos.poll();
				
				if (map[info.x][info.y] < info.age) {
					die.add(new Info(info.x, info.y, info.age));
				}
				else {
					map[info.x][info.y] -= info.age;
					q.offer(new Info(info.x, info.y, info.age + 1));
					
					if ((info.age + 1) % 5 == 0) {
						spread.add(new Info(info.x, info.y, info.age + 1));
					}
				}
			}
			
			// 여름
			// 죽은나무가 양분이 되기위해서는 우선 봄에서 전부 양분을 먹고난 뒤에 처리되어야한다.
			while (!die.isEmpty()) {
				Info info = die.poll();
				map[info.x][info.y] += (info.age / 2);
			}
			
			// 가을
			while (!spread.isEmpty()) {
				Info info = spread.poll();
				
				for (int i = 0; i < 8; ++i) {
					int ny = info.y + dy[i];
					int nx = info.x + dx[i];
					
					if (ny <= 0 || nx <= 0 || ny > N || nx > N) {
						continue;
					}
					
					q.offer(new Info(nx, ny, 1));
				}
			}
			
			// 겨울
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					map[i][j] += A[i][j];
				}
			}
			
			// 다음검사를 위해 값을 옮겨준다.
			while (!q.isEmpty()) {
				Info info = q.poll();
				infos.add(new Info(info.x, info.y, info.age));
			}
		}
		
		System.out.println(infos.size());
	}
}
