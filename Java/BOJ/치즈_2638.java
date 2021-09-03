import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class 치즈_2638 {
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static int N, M, cheeseCnt, answer;
	public static boolean[][] visited;
	public static int[][] paper;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < M; ++j) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				
				// 치즈의 개수를 저장
				if (paper[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}
		
		// 치즈의 개수가 0이 될때까지 반복
		while (cheeseCnt > 0) {
			// 방문 처리를 위한 배열을 매번 초기화 해줘야한다.
			visited = new boolean[N][M];
			// 치즈를 녹인다
			bfs();
			// 시간을 +1 해준다
			answer++;
		}
		
		System.out.println(answer);
	}
	
	public static int[] dy = {-1, 1, 0 ,0};
	public static int[] dx = {0, 0, -1, 1};
	
	// 치즈 내부에 있는 빈 공간은 외부 공기와 접촉하지 않는 것 이므로
	// 외부에서 치즈에 접근해야 녹일 수 있다
	// 때문에, 공기를 기준으로 bfs를 진행해야 정상적으로 치즈를 녹일 수 있다.
	public static void bfs() {
		Queue<Info> q = new LinkedList<>();
		// 문제의 조건에서 맨 가장자리에는 치지가 놓이지 않는 것으로 가정했으므로
		// 0, 0은 항상 공기이다.
		q.offer(new Info(0, 0));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				// 만약 방문한 적이 있는 치즈라면
				// 2변이 공기와 접촉한 것이므로 치즈를 녹인다.
				if (visited[ny][nx] && paper[ny][nx] == 1) {
					paper[ny][nx] = 0;
					cheeseCnt--;
				}
				
				// 공기라면 계속해서 탐색
				if (!visited[ny][nx] && paper[ny][nx] == 0) {
					q.offer(new Info(ny, nx));
				}
				
				// 만약 치즈가 녹는다면 0이되는데 이때, 원래는 치즈의 위치이므로
				// 탐색하면 안되지만 탐색이 될 수 있기 때문에 치즈의 위치도 방문 처리를 해준다 .
				visited[ny][nx] = true;
			}
		}
	}
}
