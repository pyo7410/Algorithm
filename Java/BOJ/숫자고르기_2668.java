import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 숫자고르기_2668 {
	public static int N, idx;
	public static boolean[] visited;
	public static int[] arr;
	public static List<Integer> answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		visited = new boolean[N + 1];
		answer = new ArrayList<Integer>();
		
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 인덱스를 기준으로 DFS를 수행
		for (int i = 1; i <= N; ++i) {
			visited[i] = true;
			// 현재 찾고자하는 인덱스를 저장
			idx = i;
			// 해당 인덱스부터 시작하여 DFS 탐색을 통해 arr에 같은 숫자가 있는지 탐색
			go(i);
			visited[i] = false;
		}
		
		// 작은 수 부터 출력해야 하므로 정렬
		Collections.sort(answer);
		
		StringBuilder sb = new StringBuilder("");
		sb.append(answer.size()).append("\n");
		
		for (int n : answer) {
			sb.append(n).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void go(int i) {
		// 조사하고자하는 idx 번호와 현재 DFS로 들어온 인데스의 값 즉 arr[i]와 같다면
		// 조건에 부합하므로 리스트에 추가
		// 조건에 부합하는 숫자만 추가되므로 문제에서 요구하는 최대 집합의 크기를 만들 수 있다.
		if (arr[i] == idx) {
			answer.add(i);
		}
		
		// 방문하지 않은 숫자라면 arr[i]의 숫자로 DFS 수행
		if (!visited[arr[i]]) {
			visited[arr[i]] = true;
			go(arr[i]);
			visited[arr[i]] = false;
		}
	}
}
