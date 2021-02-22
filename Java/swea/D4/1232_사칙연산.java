package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	public String val;
	public int left;
	public int right;
	
	public Node() {
		
	}
	
	public Node(String v, int l, int r) {
		this.val = v;
		this.left = l;
		this.right = r;
	}
}

public class 사칙연산_1232 {
	public static int N;
	public static Node[] tree = new Node[1001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				// 인덱스는 전부 나오므로 첫 번째 입력은 버려
				st.nextToken();
				
				if (st.countTokens() > 1) {
					tree[i] = new Node(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				}
				else {
					tree[i] = new Node(st.nextToken(), -1, -1);
				}
			}
			
			for (int i = N; i > 0; i--) {
				if (tree[i].left > 0 && tree[i].right > 0) {
					tree[i].val = Double.toString(calc(Double.parseDouble(tree[tree[i].left].val), Double.parseDouble(tree[tree[i].right].val), tree[i].val));
				}
			}
			
			st = new StringTokenizer(tree[1].val, ".");
			tree[1].val = st.nextToken();
			
			sb.append("#").append(tc).append(" ").append(tree[1].val).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static double calc(Double x, Double y, String op) {
		if (op.equals("+")) {
			return x + y;
		}
		else if (op.equals("-")) {
			return x - y;
		}
		else if (op.equals("*")) {
			return x * y;
		}
		
		return x / y;
	}
}
