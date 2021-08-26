import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질4_13913 {
	public static class Info {
		int cur, cnt;

		public Info(int cur, int cnt) {
			this.cur = cur;
			this.cnt = cnt;
		}
	}

	public final static int MAX = 100000;
	public static int N, K;
	public static StringBuilder sb;
	public static boolean[] visited;
	public static int[] route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[MAX + 1];
		route = new int[MAX + 1];

		Arrays.fill(route, -1);

		bfs();
	}

	public static void bfs() {
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(N, 0));
		visited[N] = true;

		while (!q.isEmpty()) {
			Info info = q.poll();

			if (info.cur == K) {
				System.out.println(info.cnt);

				sb = new StringBuilder();
				print(K);

				System.out.println(sb);
				return;
			}

			int next = info.cur * 2;
			if (next <= MAX && !visited[next]) {
				q.offer(new Info(next, info.cnt + 1));
				visited[next] = true;
				route[next] = info.cur;
			}

			next = info.cur + 1;
			if (next <= MAX && !visited[next]) {
				q.offer(new Info(next, info.cnt + 1));
				visited[next] = true;
				route[next] = info.cur;
			}

			next = info.cur - 1;
			if (next >= 0 && !visited[next]) {
				q.offer(new Info(next, info.cnt + 1));
				visited[next] = true;
				route[next] = info.cur;
			}
		}
	}

	public static void print(int n) {
		if (route[n] > -1) {
			print(route[n]);
		}

		sb.append(n).append(" ");
	}
}
