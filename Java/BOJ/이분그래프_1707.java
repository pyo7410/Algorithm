import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이분그래프_1707 {
	public static int K, V, E;
	public static int[] visited;
	public static List<Integer>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			visited = new int[V + 1];
			tree = new ArrayList[V + 1];
			
			for (int i = 1; i <= V; ++i) {
				tree[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < E; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				tree[from].add(to);
				tree[to].add(from);
			}
			
			boolean flag = true;
			// 입력으로 연결이 안된 그래프도 주어질 수 있으므로
			// 모든 점을 검사하며 방문하지 않은점을 모두 탐색하도록 한다.
			for (int i = 1; i <= V; ++i) {
				if (visited[i] == 0) {
					// flag = dfs(i, 1);	// dfs 방식
					flag = bfs(i);			// bfs 방식
				}
				
				// flag가 false이면 이는 이분그래프가 아닌 그래프라는 의미이므로
				// 문제에 의해 더이상 조사를 하지 않아도 되므로 break를 수행한다.
				if (!flag) {
					break;
				}
			}
			
			sb.append((flag) ? "YES\n" : "NO\n");
		}
		
		System.out.println(sb);
	}
	
	// 그룹을 1과 -1로 나눔
	public static boolean dfs(int cur, int group) {
		visited[cur] = group;
		
		for (int next : tree[cur]) {
			// 방문한적이 없다면
			if (visited[next] == 0) {
				// 방문하면서 return값이 false이면 false를 반환하도록 하고
				// true일 경우는 다음 정점도 검색을 하기위해 return을 통해 반환을 시키면 안된다.
				if (dfs(next, -group) == false) {
					return false;
				}
			}
			
			// 위에 if문에서 return을 처리하므로 자동적으로 방문하지 않을 경우가 되고
			// 또한, 현재 정점과 다음 정점의 group이 같다면 이는 이분그래프가 아니므로 false를 반환
			if (visited[next] == visited[cur]) {
				return false;
			}
		}
		
		// for문을 정상적으로 수행했다면 이분그래프이므로 true를 반환
		return true;
	}
	
	public static boolean bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visited[i] = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : tree[cur]) {
				// 방문하지않은 점이라면 bfs 수행
				if (visited[next] == 0) {
					q.offer(next);
					// 그룹은 1, -1로 나눔
					visited[next] = -visited[cur];
				}
				else if (visited[next] == visited[cur]) {
					// 만약 방문한 점이면서 현재 정점과 같은 그룹이라면
					// 이분그래프가 될 수 없으므로 false를 반환
					return false;
				}
			}
		}
		
		// 모든 bfs과정을 성공적으로 끝냈다면 이는 이분 그래프이므로 true를 반환
		return true;
	}
}
