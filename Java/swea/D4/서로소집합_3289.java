package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서로소집합_3289 {
	public static int N, M;
	public static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N + 1];
			make();
			
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int cur = Integer.parseInt(st.nextToken());
				
				if (cur == 0) {
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				}
				else if (cur == 1) {
					sb.append(isUnion(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void make() {
		for (int i = 1; i < N + 1; ++i) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		}
		
		return parents[x] = findSet(parents[x]);
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) {
			return;
		}
		
		parents[bRoot] = aRoot;
		return;
	}
	
	public static int isUnion(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) {
			return 1;
		}
		
		return 0;
	}
}
