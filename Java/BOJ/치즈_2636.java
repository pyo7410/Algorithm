import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈_2636 {
	public static int N, M, cheeseCnt, time, answer;
	public static boolean[][] visited;
	public static int[][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if (board[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}
		
		// 치즈의 개수가 0이되기 직전의 수를 기억하며
		// 치즈를 녹여간다.
		while (cheeseCnt > 0) {
			time++;
			// 치즈의 개수를 먼저 세고
			// 공기중에 노출된 치즈를 제거해야 문제에서 요구하는 답을 찾을 수 있다.
			answer = cheeseCnt;
			
			// 치즈로 둘러 싸이지 않은 곳은 전부 공기이고
			// 문제에서 주어진대로 X친 부분 즉 board의 가장자리 부분은 무조건 공기가 되므로 
			// (0, 0)은 무조건 공기!
			bfs(0, 0);
		}
		
		System.out.println(time);
		System.out.println(answer);
	}
	
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static void bfs(int i, int j) {
		// 치즈를 탐색하는게 아니고
		// 공기를 탐색해서 공기와 닿는 치즈부분을 없에준다!
		
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(i, j));
		
		visited = new boolean[N][M];
		visited[i][j] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int dir = 0; dir < 4; ++dir) {
				int ny = info.y + dy[dir];
				int nx = info.x + dx[dir];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx]) {
					continue;
				}
				
				// 치즈라면 공기중에 닿고 있으므로 전체 치즈개수에서 빼주고 맵도 갱신
				if (board[ny][nx] == 1) {
					cheeseCnt--;
					board[ny][nx] = 0;
				}				
				else if (board[ny][nx] == 0) {
					// 계속해서 다음 공기를 찾기위해 탐색
					q.offer(new Info(ny, nx));
				}
				
				// 치즈가 녹아 0이되더라도 그 자리는 이미 방문 했으므로
				// 치즈든 공기든 방문체크가 필수
				visited[ny][nx] = true;
			}
		}
	}
}
