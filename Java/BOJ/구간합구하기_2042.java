import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_2042 {
	public static int N, M, K;
	public static long[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder("");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree segmentTree = new SegmentTree(N, arr);
		
		int cnt = M + K;
		while (cnt-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			// b는 1부터 시작하므로 -1을 해주어 인덱스와 맞춰준다.
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {
				// 바꾸고자하는 위치의 원래 숫자와 바꾸고자하는 숫자의 차를 미리 구해서 전달
				segmentTree.update(1, 0, N - 1, b, c - arr[b]);
				// 위치를 바꿈
				arr[b] = c;
			}
			else {
				sb.append(segmentTree.sum(1, 0, N - 1, b, (int)c - 1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static class SegmentTree {
		long[] tree;
		
		public SegmentTree(int N, long[] arr) {
			tree = new long[N * 4];
			init(0, N - 1, 1, arr);
		}
		
		long init(int start, int end, int node, long[] arr) {
			// 리프노드라면
			if (start == end) {
				return tree[node] = arr[start];
			}
			
			// 만약 리프노드가 아니라면 자식노드를 탐색하며 그 구간의 합을 구해 갱신
			int mid = (start + end) / 2;
			return tree[node] = init(start, mid, node * 2, arr) + init(mid + 1, end, (node * 2) + 1, arr);
		}
		
		long sum(int node, int start, int end, int left, int right) {
			// 만약 구간합을 구하고자하는 범위를 벗어났다면 해당 숫자는 구간합이 될 수 없으므르 0을 리턴
			if (left > end || right < start) {
				return 0;
			}
			
			// 구하고자 하는 범위인 left와 right사이에 start와 end가 포함되면
			// node의 자식들은 자동으로 구간합에 포함되기 때문에 tree[node]를 반환
			if (left <= start && end <= right) {
				return tree[node];
			}
			
			// 만약 위 두 경우가 아니라면 자식노드를 탐색하며 구간을 찾는다
			int mid = (start + end) / 2; 
			return sum(node * 2, start, mid, left, right) + sum((node * 2) + 1, mid + 1, end, left, right);
		}
		
		void update(int node, int start, int end, int idx, long diff) {
			// 범위를 벗어나면 구간합을 업데이트할 필요가 없으므로
			if (idx < start || end < idx) {
				return;
			}
			
			// 노드를 찾아 내려가면서 갱신
			// 바꾸고자하는 자리의 숫자와 원래 있던 숫자의 변화값을 미리 구해서
			// 전달했기때문에 그 값을 더해주면된다.
			// 만약 1 -> 3으로 바꾸면 그 차이는 2이므로 바꾼 지점의 구간합들에 +2를 더하면 된다.
			// 만약 3 -> 1로 바꾸면 그 차이는 -2이므로 바꾼 지점의 구간합들에 -2를 더하면 된다.
			tree[node] += diff;
			
			// 리프노드가 아니라면 자식 노드도 갱신시켜준다.
			if (start != end) {
				int mid = (start + end) / 2;
				update(node * 2, start, mid, idx, diff);
				update((node * 2) + 1, mid + 1, end, idx, diff);
			}
		}
	}
}


