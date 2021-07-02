import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질2_12851 {
	public static int N, K, answer, answerCnt;
	public static boolean[] visited = new boolean[100001]; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		answer = Integer.MAX_VALUE;
		bfs();
		
		System.out.println(answer);
		System.out.println(answerCnt);
	}
	
	public static class Info {
		int cur, cnt;

		public Info(int cur, int cnt) {
			super();
			this.cur = cur;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(N, 0));
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			// BFS 특성상 작은 시간대의 순서가 먼저 수행되므로 큰 시간대가 나왔다면
			// 나머지는 더 큰 시간대 이므로 볼 필요가 없다
			if (answer < info.cnt) {
				return;
			}
			
			// 방문처리를 큐에서 꺼낼때 진행하여 현재 시간대에 있는 모든 경로를 한번에 담아
			// 모든 경우의 수를 구할 수 있게한다.
			visited[info.cur] = true;
			
			// 동생이 있는 위치라면
			if (info.cur == K) {
				// 처음으로 방문하는 경우라면 answer값 갱신
				if (answer == Integer.MAX_VALUE) {
					answer = info.cnt;
				} 
				
				// 경우의 수 + 1
				if (answer == info.cnt) {
					answerCnt++;
				}
			}
			
			int[] nextPoint = {info.cur * 2, info.cur - 1, info.cur + 1};
			for (int i = 0; i < 3; ++i) {
				if (nextPoint[i] < 0 || nextPoint[i] > 100000 || visited[nextPoint[i]]) {
					continue;
				}
				
				q.offer(new Info(nextPoint[i], info.cnt + 1));
			}
		}
	}
}
