import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR_9019 {
	public static int T;
	public static String answer;
	public static boolean[] visited;
	public static int A, B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			visited = new boolean[10001];
			
			bfs(A);
			
			System.out.println(answer);
		}
	}
	
	public static class Info {
		String str;
		int n;
		
		public Info(String str, int n) {
			super();
			this.str = str;
			this.n = n;
		}
	}
	
	public static void bfs(int n) {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info("", n));
		visited[n] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			// BFS이므로 가장 빨리 B에 접근한 방법이 최소 개수가 된다!
			if (info.n == B) {
				answer = info.str;
				return;
			}
			
			int d = D(info.n);
			if (!visited[d]) {
				q.offer(new Info(info.str + "D", d));
				visited[d] = true;
			}
			
			int s = S(info.n);
			if (!visited[s]) {
				q.offer(new Info(info.str + "S", s));
				visited[s] = true;
			}
			
			int l = L(info.n);
			if (!visited[l]) {
				q.offer(new Info(info.str + "L", l));
				visited[l] = true;
			}
			
			int r = R(info.n);
			if (!visited[r]) {
				q.offer(new Info(info.str + "R", r));
				visited[r] = true;
			}
		}
	}
	
	public static int D(int num) {
		return (num * 2) % 10000;
	}
	
	public static int S(int num) {
		int result = num - 1;
		return (result < 0) ? 9999 : result;
	}
	
	public static int L(int num) {
		// 0 ~ 9999까지 이므로 숫자가 4자리라면 처음 숫자를 맨 뒤로 보내주고
		// 4자리가 아니라면 한 자리씩 왼쪽으로 밀어준다.
		return (num % 1000) * 10 + num / 1000;
	}
	
	public static int R(int num) {
		// 0 ~ 9999까지 이므로 숫자가 4자리라면 마지막 숫자를 맨 앞으로 보내주고
		// 4자리가 아니라면 한 자리씩 오른쪽으로 밀어준다.
		return (num % 10) * 1000 + num / 10;
	}
}
