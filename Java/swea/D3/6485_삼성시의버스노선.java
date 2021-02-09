package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 삼성시의버스노선_6485 {
	public static int[] a;
	public static int[] b;
	public static int[] c;
	public static int[] answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			int N = Integer.parseInt(br.readLine());
			
			a = new int[N];
			b = new int[N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			int P = Integer.parseInt(br.readLine());
			
			c = new int[P];
			for (int i = 0; i < P; ++i) {
				c[i] = Integer.parseInt(br.readLine());
			}
			
			answer = new int[P];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < P; ++j) {
					if (c[j] >= a[i] && c[j] <= b[i]) {
						answer[j]++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < P; ++i) {
				sb.append(answer[i]).append(" ");
			}
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
