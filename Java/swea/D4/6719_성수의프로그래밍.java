package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 성수의프로그래밍_6719 {
	public static int N, K;
	public static double answer;
	public static Integer[] subject;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			subject = new Integer[N];
			
			answer = 0;
			
			st = new StringTokenizer(br.readLine(), " ");			
			for (int i = 0; i < N; ++i) {
				subject[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(subject);
			
			for (int i = N - K; i < N; ++i) {
				answer = (answer + (double)subject[i]) / 2.0;
			}
			
			sb.append("#").append(tc).append(" ").append(String.format("%.6f", answer)).append("\n");
		}
		
		System.out.println(sb);
	}
}
