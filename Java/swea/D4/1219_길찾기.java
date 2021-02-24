package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 길찾기_1219 {
	public static int N, answer;
	public static int[] edge1, edge2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			N = Integer.parseInt(st.nextToken());
			
			edge1 = new int[100];
			edge2 = new int[100];
			
			for (int i = 0; i < 100; ++i) {
				edge1[i] = -1;
				edge2[i] = -1;
			}
			
			answer = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				if (edge1[idx] > 0) {
					edge2[idx] = val;
				}
				else {
					edge1[idx] = val;
				}
			}
			
			go(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void go(int val) {
		if (val == -1) {
			return;
		}
		
		if (val == 99) {
			answer = 1;
			return;
		}
		
		if (answer == 1) {
			return;
		}
		
		go(edge1[val]);
		go(edge2[val]);
	}
}
