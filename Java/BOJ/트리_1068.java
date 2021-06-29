import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리_1068 {
	public static int N, root, removeNode, answer;
	public static boolean[] visited;
	public static List<Integer>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		
		// 이 문제에서의 트리는 이진트리가 아님을 주의
		// 즉, 한 노드의 자식으로 2개 이상의 노드가 주어질 수도 있다.
		tree = new ArrayList[N];
		
		for (int i = 0; i < N; ++i) {
			tree[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; ++i) {
			int node = Integer.parseInt(st.nextToken());
			
			if (node != -1) {
				tree[i].add(node);
				tree[node].add(i);
			}
			else {
				root = i;
			}
		}
		
		removeNode = Integer.parseInt(br.readLine());
		// 지우고자하는 노드를 미리 방문처리해서 DFS를 하지 못하게 한다.
		visited[removeNode] = true;
		
		// 루트노드가 지워지면 리프노드의 개수는 0이되므로 이를 처리하기 위함
		if (!visited[root]) {
			go(root);
		}
		
		System.out.println(answer);
	}
	
	public static void go(int node) {
		// 현재 노드를 방문 처리
		visited[node] = true;
		
		int childCnt = 0;
		for (int i = 0; i < tree[node].size(); ++i) {
			int nextNode = tree[node].get(i);
			
			// ArrayList에 예를 들어 -1 0 0 1 1 이라면
			// 0 : 1, 2
			// 1 : 0, 3, 4
			// 2 : 0
			// 3 : 1
			// 4 : 1
			// 처럼 저장되지만 1번 인덱스의 경우 0번은 부모노드인데 이때,
			// 부모노드인 경우 visited가 이미 true가 되어있으므로 방문을 다시 안하고
			// 자식노드만 방문하게 된다.
			if (!visited[nextNode]) {
				childCnt++;
				visited[nextNode] = true;
				go(nextNode);
			}
		}
		
		// 자식 노드가 없다면 이는 리프노드이므로
		if (childCnt == 0) {
			answer++;
		}
	}
}
