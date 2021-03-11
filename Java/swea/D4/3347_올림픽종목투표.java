package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 올림픽종목투표_3347 {
	public static int N, M, answer, maxVoteNum;
	public static int[] vote;
	public static int[] A;
	public static int[] B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine()," ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			vote = new int[N];
			A = new int[N];
			B = new int[M];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; ++i) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < M; ++i) {
				for (int j = 0; j < N; ++j) {
					if (B[i] >= A[j]) {
						vote[j]++;
						break;
					}
				}
			}
			
			maxVoteNum = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; ++i) {
				if (maxVoteNum < vote[i]) {
					maxVoteNum = vote[i];
					answer = i + 1;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
