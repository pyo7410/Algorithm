package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 장훈이의높은선반_1486 {
	public static int N, B, answer;
	public static int[] people = new int[20];
	public static boolean[] isSelected = new boolean[20];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				people[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			powerset(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void powerset(int cnt) {
		if (cnt == N) {
			int sum = 0;
			
			for (int i = 0; i < N; ++i) {
				if (isSelected[i]) {
					sum += people[i];
				}
			}
			
			if (sum >= B) {
				sum -= B;
				answer = (answer > sum) ? sum : answer;
			}
			
			return;
		}
		
		isSelected[cnt] = true;
		powerset(cnt + 1);
		isSelected[cnt] = false;
		powerset(cnt + 1);
	}
}
