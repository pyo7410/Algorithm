import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자_1194 {
	public static class Info {
		int y;
		int x;
		
		// 이동횟수
		int cnt;
		
		// a ~ f개의 키 즉, 6개를 비트마스킹을 사용해 검사
		int keys;
		
		public Info(int y, int x, int cnt, int keys) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.keys = keys;
		}
	}
	
	public static int N, M;
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static char[][] maze;
	public static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		
		// 6개의 키를 비트마스킹으로 표현하면 2^7 - 1 즉, 63이 된다.
		visited = new boolean[N][M][64];
		
		Info start = null;
		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			
			for (int j = 0; j < M; ++j) {
				maze[i][j] = input.charAt(j);
				
				if (maze[i][j] == '0') {
					start = new Info(i, j, 0, 0);
				}
			}
		}
		
		int answer = 0;
		
		Queue<Info> q = new LinkedList<Info>();
		q.offer(start);
		visited[start.y][start.x][start.keys] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			if (maze[info.y][info.x] == '1') {
				answer = info.cnt;
				break;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				char cur = maze[ny][nx];
				int keys = info.keys;
				
				if (!visited[ny][nx][keys] && cur != '#') {
					if (cur >= 'a' && cur <= 'f') {
						// 먹은 키에 맞는 비트를 1로 바꾸어준다.
						keys |= 1 << (cur - 'a');
					}
					else if (cur >= 'A' && cur <= 'F') {
						// 문을 열수있는지 검사
						if ((keys & (1 << (cur - 'A'))) == 0) {
							// 열 수 없다면 전진 불가능!
							continue;
						}
					}
					
					visited[ny][nx][keys] = true;
					q.offer(new Info(ny, nx, info.cnt + 1, keys));
				}
			}
		}
		
		System.out.println((answer == 0) ? -1 : answer);
	}
}
