import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현_1717 {
	public static int n, m;
	public static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder("");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parents = new int[n + 1];
		
		make();
		
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (op == 0) {
				union(a, b);
				continue;
			}
			
			if (findSet(a) == findSet(b)) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static void make() {
		for (int i = 0; i <= n; ++i) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if (parents[a] == a) {
			return a;
		}
		
		return parents[a] = findSet(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		parents[bRoot] = aRoot;
	}
}
