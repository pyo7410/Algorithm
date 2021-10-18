package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Contact_1238 {
	public static int len, start, answer;
	public static int[] visit;
	public static List<Integer>[] edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			len = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			visit = new int[101];
			edge = new ArrayList[101];
			
			answer = Integer.MIN_VALUE;
			
			for (int i = 1; i <= 100; ++i) {
				edge[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < len / 2; ++i) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				edge[from].add(to);
			}
			
			visit[start] = 1;
			go();
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static class Info {
		int vertex;
		int cnt;
		
		public Info(int vertex, int cnt) {
			this.vertex = vertex;
			this.cnt = cnt;
		}
	}
	
	// dfs로 쓸 시 동시에 퍼져나가는게 아닌 끝까지 간 다음 다시 돌아오므로
	// 만약 동시에 퍼져나가야 하는 부분이 있는데 이 부분은 이미 방문한 것이 되버려
	// 다른 값이 나올 수 있다.
	public static void go() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		int maxCnt = Integer.MIN_VALUE;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < edge[cur].size(); ++i) {
				int v = edge[cur].get(i);
				
				if (visit[v] != 0) {
					continue;
				}
				
				visit[v] = visit[cur] + 1;
				q.offer(v);
			}
			
			// bfs이므로 계속 갱신하다보면 마지막에 갱신된는 값이 최대 값이된다.
			maxCnt = visit[cur];
		}
		
		for (int i = 1; i < 101; ++i) {
			if (maxCnt != visit[i]) {
				continue;
			}
			
			answer = (answer < i) ? i : answer;
		}
	}
}


