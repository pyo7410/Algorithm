import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE_13023 {
	public static int N, M, answer;
	public static boolean[] visited;
	public static List<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		list = new ArrayList[N];
		
		for (int i = 0; i < N; ++i) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향으로 연결되있다!
			// a 와 b가 친구라면 b a라 해도 같은 a b이므로 친구 관계가 되야하므로
			list[from].add(to);
			list[to].add(from);
		}
		
		for (int i = 0; i < N; ++i) {
			// i가 dfs의 시작노드가 된다.
			// 한개의 노드를 방문했으므로 cnt를 1로 설정한다.
			go(i, 1);
			
			// 문제에 조건에 맞는 A B C D E가 존재한다는 의미이므로 더이상 탐색 X
			if (answer > 0) {
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	public static void go(int cur, int cnt) {
		// 문제에서 요구하는 것은 그래프탐색을 통해 A B C D E 5개 노드 
		// 즉, 5개의 노드를 방문할 수 있냐를 요구하고있으므로 5개의 노드를 찾았다면 리턴
		if (cnt == 5) {
			answer = 1;
			return;
		}
		
		// 현재 노드 방문처리
		visited[cur] = true;
		// 현재 노드와 연결된 노드들을 탐색
		for (int next : list[cur]) {
			// 방문을 안했다면 노드 갯수를 +1 해주고 방문
			if (!visited[next]) {
				go(next, cnt + 1);
			}
		}
		// 다음 dfs 탐색을 위해 false로 변경
		visited[cur] = false;
	}
}
