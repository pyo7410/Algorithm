package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 중위순회_1231 {
	public static int N;
	public static StringBuilder sb = new StringBuilder("");
	public static char[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			tree = new char[N + 1];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				tree[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
			}
			
			sb.append("#").append(tc).append(" ");
			
			inOrder(1);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void inOrder(int idx) {
		if (idx > N) {
			return;
		}
		
		inOrder(idx * 2);
		sb.append(tree[idx]);
		inOrder((idx * 2) + 1);
	}
}
