package D1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 중간값찾기_2063 {
	public static int[] count = new int[101];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		String score = br.readLine();
		StringTokenizer st = new StringTokenizer(score);
		
		for (int i = 0; i < N; ++i) {
			count[Integer.parseInt(st.nextToken())]++;
		}
		
		int answer = 0;
		int ranking = 0;
		for (int i = 0; i <= 100; i++) {
			ranking += count[i];
			if (N / 2 < ranking) {
				answer = i;
				break;
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}
