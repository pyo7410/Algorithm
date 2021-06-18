import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크_5014 {
	public static int F, S, G, U, D, answer;
	public static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F + 1];
		answer = bfs();
		System.out.println((answer == -1) ? "use the stairs" : answer);
	}
	
	public static class Info {
		int cur, cnt;

		public Info(int cur, int cnt) {
			super();
			this.cur = cur;
			this.cnt = cnt;
		}
	}
	
	public static int bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(S, 0));
		visited[S] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			// bfs이기때문에 먼저 G에 들어오는 횟수가 가장 적은 횟수가된다!
			if (info.cur == G) {
				return info.cnt;
			}
			
			if (info.cur + U <= F && !visited[info.cur + U]) {
				visited[info.cur + U] = true;
				q.offer(new Info(info.cur + U, info.cnt + 1));
			}
			if (info.cur - D > 0 && !visited[info.cur - D]) {
				visited[info.cur - D] = true;
				q.offer(new Info(info.cur - D, info.cnt + 1));
			}
		}
		
		// bfs를 다 돌아도 해당층을 못갔다면 -1을 반환
		return -1;
	}
}
